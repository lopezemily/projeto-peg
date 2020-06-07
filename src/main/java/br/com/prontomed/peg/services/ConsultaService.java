package br.com.prontomed.peg.services;

import br.com.prontomed.peg.dto.Disponibilidade;
import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Especialidade;
import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.repositories.ConsultaRepository;
import br.com.prontomed.peg.repositories.EspecialidadeRepository;
import br.com.prontomed.peg.repositories.MedicoRepository;
import br.com.prontomed.peg.repositories.PacienteRepository;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    private final static LocalTime inicioAtendimento = LocalTime.of(8, 0);
    private final static LocalTime fimAtendimento = LocalTime.of(18, 0);
    private final static LocalTime inicioAlmoco = LocalTime.of(12, 0);
    private final static LocalTime fimAlmoco = LocalTime.of(14, 0);
    private final static int tempoConsultaMinutos = 30;

    public void criarConsulta(String cpf, Consulta consulta) {
        Paciente paciente = pacienteRepository.getOne(cpf);
        consulta.setPaciente(paciente);
        
        Especialidade especialidade = especialidadeRepository.getOne((long) consulta.getEspecialidade().getId());
        consulta.setEspecialidade(especialidade);
        
        DayOfWeek diaSemana = consulta.getData().getDayOfWeek();
        List<Medico> medicosDisponiveis = medicoRepository.findAllByEspecialidadesAndDisponibilidade(especialidade, diaSemana);
        Medico medico = medicosDisponiveis.get(0);
        consulta.setMedico(medico);
        
        consulta.setHoraFim(consulta.getHoraInicio().plusMinutes(tempoConsultaMinutos));

        consultaRepository.save(consulta);
    }
    
    public void criarConsulta(Consulta consulta) {
        criarConsulta(consulta.getPaciente().getCpf(), consulta);
    }

    public List<Consulta> obterConsultasAnterioresPaciente(String cpf) {
        return consultaRepository.findByRealizadaAndPacienteCpf(true, cpf);
    }

    public List<Consulta> obterConsultasProximasPaciente(String cpf) {
        return consultaRepository.findConsultasProximasByPacienteCpf(cpf);
    }

    public List<Consulta> obterConsultasProximasMedico(String cpf) {
        return consultaRepository.findConsultasDoDiaByMedicoCpf(cpf);
    }
    
    public List<Consulta> obterConsultasProximasTodasMedico(String cpf) {
        return consultaRepository.findConsultasByMedicoCpf(cpf);
    }
    
    public List<Consulta> obterConsultasProximasTodas() {
        return consultaRepository.findConsultas();
    }
    
    public List<Consulta> obterConsultasDia(){
        return consultaRepository.findConsultasDoDia();
    }

    public void registrarProntuario(long numAtendimento, Prontuario prontuario) {
        Optional<Consulta> consulta = consultaRepository.findById(numAtendimento);

        if (consulta.isPresent()) {
            consulta.get().setProntuario(prontuario);
            consulta.get().setRealizada(true);
            consulta.get().setConfirmada(true);
            consultaRepository.save(consulta.get());
        } else {
            throw new RuntimeException("Consulta não cadastrada!");
        }
    }

    public void confirmarConsulta(long numAtendimento) {
        Consulta consulta = consultaRepository.findById(numAtendimento).get();

        if (!consulta.isConfirmada()) {
            consulta.setConfirmada(true);
            consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Consulta já confirmada!");
        }
    }

    public Consulta obterConsulta(long numAtendimento) {
        Consulta consulta = consultaRepository.findById(numAtendimento).get();
        return consulta;
    }

    public List<LocalTime> obterHorariosDisponiveis(LocalDate data, int especialidadeId) throws Exception {
        DayOfWeek diaSemana = data.getDayOfWeek();
        Especialidade especialidade = especialidadeRepository.getOne((long) especialidadeId);
        List<Medico> medicosDisponiveis = medicoRepository.findAllByEspecialidadesAndDisponibilidade(especialidade, diaSemana);

        if (medicosDisponiveis.isEmpty())
            throw new Exception("Não há médicos com esta especialidade atendendo neste dia da semana");

        List<Consulta> consultasNoDia = consultaRepository.findAllByDataAndMedicoIn(data, medicosDisponiveis);

        LocalTime inicioAtendimentoDia = inicioAtendimento;
        if (data.compareTo(LocalDate.now()) == 0) {
            inicioAtendimentoDia = LocalTime.of(LocalTime.now().plusHours(1).getHour(), 0);
            if(inicioAtendimentoDia.compareTo(fimAtendimento) >= 0)
                throw new Exception("Não há mais horários disponíveis para hoje.");
        }

        int tempoMinutosTotal = (int) Duration.between(inicioAtendimentoDia, fimAtendimento).toMinutes();
        int consultasTotaisDia = tempoMinutosTotal / tempoConsultaMinutos;

        List<Disponibilidade> horariosDisponiveis = new ArrayList<>();
        for (Medico medico : medicosDisponiveis) {
            LocalTime atual = inicioAtendimentoDia;
            
            for (int i = 0; i < consultasTotaisDia - 1; i++) {
                if(atual.compareTo(inicioAlmoco) < 0 || atual.compareTo(fimAlmoco) >= 0){
                    horariosDisponiveis.add(new Disponibilidade(medico.getCpf(), medico.getNome(), atual));
                }
                atual = atual.plusMinutes(tempoConsultaMinutos);
            }
        }

        for (Consulta consulta: consultasNoDia){
            Disponibilidade disponibilidade = new Disponibilidade(consulta.getMedico().getCpf(), consulta.getMedico().getNome(), consulta.getHoraInicio());
            horariosDisponiveis.remove(disponibilidade);
        }

        return horariosDisponiveis.stream().map(h -> h.getHorario()).distinct().collect(Collectors.toList());
    }

    public void cancelarConsulta(long numAtendimento) {
        consultaRepository.deleteById(numAtendimento);
    }
}
