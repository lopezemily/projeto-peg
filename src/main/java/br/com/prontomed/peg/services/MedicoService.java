package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.repositories.ConsultaRepository;
import br.com.prontomed.peg.repositories.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public Medico obterMedico(String medicoId) {
        return medicoRepository.getOne(medicoId);
    }

    public int obterContagemPacientesAgendadosMes(String medicoId) {
        return consultaRepository.countConsultaMesByMedicoCpf(medicoId);
    }

    public int obterContagemPacientesAtendidosMes(String medicoId) {
        return consultaRepository.countConsultaMesByMedicoCpfAndRealizadaAndConfirmada(medicoId, true, true);
    }

    public int obterContagemPacientesConfirmadosMes(String medicoId) {
        return consultaRepository.countConsultaMesConfirmadosByMedicoCpf(medicoId);
    }

    public int obterContagemPacientesAusentesMes(String medicoId) {
        return consultaRepository.countConsultaMesAusenteByMedicoCpf(medicoId);
    }

	public void inserirMedico(Medico medico) {
        medicoRepository.save(medico);
	}
}
