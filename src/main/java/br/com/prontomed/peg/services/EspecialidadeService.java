package br.com.prontomed.peg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prontomed.peg.models.Especialidade;
import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.repositories.EspecialidadeRepository;
import br.com.prontomed.peg.repositories.MedicoRepository;

@Service
public class EspecialidadeService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

	public List<Especialidade> obterEspecialidadesComMedicosAssociados() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream().flatMap(m -> m.getEspecialidades().stream()).distinct().collect(Collectors.toList());
	}

	public List<Especialidade> obterTodasEspecialidades() {
		return especialidadeRepository.findAll();
	}
    
}
