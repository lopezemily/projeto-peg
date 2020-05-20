package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.models.Prontuario;
import br.com.prontomed.peg.repositories.ConsultaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void criarConsulta(Consulta consulta) {
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

    public void confirmarConsulta(Consulta consulta) {
        if (!consulta.isConfirmada()) {
            consulta.setConfirmada(true);
        } else {
            throw new RuntimeException("Consulta já confirmada!");
        }
    }
}
