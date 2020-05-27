package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Especialidade;
import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.repositories.ConsultaRepository;
import br.com.prontomed.peg.repositories.EspecialidadeRepository;
import br.com.prontomed.peg.repositories.MedicoRepository;
import br.com.prontomed.peg.repositories.PacienteRepository;
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
}
