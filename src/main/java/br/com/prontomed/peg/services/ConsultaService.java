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

    public void criarConsulta(String cpf, Consulta consulta) {
        Paciente paciente = pacienteRepository.getOne(cpf);
        consulta.setPaciente(paciente);

        Medico medico = medicoRepository.getOne("12300012300");
        consulta.setMedico(medico);

        Especialidade especialidade = especialidadeRepository.getOne(consulta.getEspecialidade().getId());
        consulta.setEspecialidade(especialidade);

        consultaRepository.save(consulta);
    }

    public List<Consulta> obterConsultasAnterioresPaciente(String cpf) {
        return consultaRepository.findByRealizadaAndPacienteCpf(true, cpf);
    }

    public List<Consulta> obterConsultasProximasPaciente(String cpf) {
        return consultaRepository.findByRealizadaAndPacienteCpf(false, cpf);
    }

    public List<Consulta> obterConsultasAnterioresMedico(String cpf) {
        return consultaRepository.findByRealizadaAndMedicoCpf(true, cpf);
    }

    public List<Consulta> obterConsultasProximasMedico(String cpf) {
        return consultaRepository.findByRealizadaAndMedicoCpf(false, cpf);
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

    public List<Disponibilidade> obterHorariosDisponiveis(LocalDate data, String especialidadeDescricao) throws Exception {
        DayOfWeek diaSemana = data.getDayOfWeek();
        Especialidade especialidade = especialidadeRepository.findOneByDescricao(especialidadeDescricao);
        List<Medico> medicosDisponiveis = medicoRepository.findAllByEspecialidadesAndDisponibilidade(especialidade, diaSemana);

        if (medicosDisponiveis.isEmpty())
            throw new Exception("Não há médicos com esta especialidade atendendo neste dia da semana");

        List<Consulta> consultasNoDia = consultaRepository.findAllByDataAndMedicoIn(data, medicosDisponiveis);

        LocalTime inicioAtendimento = LocalTime.of(8, 0);
        LocalTime fimAtendimento = LocalTime.of(18, 0);
        LocalTime inicioAlmoco = LocalTime.of(12, 0);
        LocalTime fimAlmoco = LocalTime.of(14, 0);

        int tempoMinutosTotal = (int) Duration.between(inicioAtendimento, fimAtendimento).toMinutes();
        int tempoConsultaMinutos = 30;
        int consultasTotaisDia = tempoMinutosTotal / tempoConsultaMinutos;

        List<Disponibilidade> horariosDisponiveis = new ArrayList<>();
        for (Medico medico : medicosDisponiveis) {
            LocalTime atual = inicioAtendimento;
            
            for (int i = 0; i < consultasTotaisDia - 1; i++) {
                atual = atual.plusMinutes(tempoConsultaMinutos);
                if(atual.compareTo(inicioAlmoco) < 0 || atual.compareTo(fimAlmoco) >= 0){
                    horariosDisponiveis.add(new Disponibilidade(medico.getCpf(), medico.getNome(), atual));
                }
            }
        }

        for (Consulta consulta: consultasNoDia){
            Disponibilidade disponibilidade = new Disponibilidade(consulta.getMedico().getCpf(), consulta.getMedico().getNome(), consulta.getHoraInicio());
            horariosDisponiveis.remove(disponibilidade);
        }

        return horariosDisponiveis;
    }
}
