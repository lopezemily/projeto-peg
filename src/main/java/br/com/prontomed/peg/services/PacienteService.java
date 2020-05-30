package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Paciente;
import br.com.prontomed.peg.repositories.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository repository;
    
    public void inserirPaciente(Paciente paciente){
        if(repository.existsById(paciente.getCpf())){
            throw new RuntimeException("Paciente já cadastrado!");
        }
        
        repository.save(paciente);
    }
    
    public void atualizarPaciente(Paciente paciente){
        if(repository.existsById(paciente.getCpf())){
            repository.save(paciente);
        } else {
            throw new RuntimeException("Paciente não cadastrado!");
        }
    }
    
    public void atualizarAlergias(String cpf, List<String> alergias){
        Optional<Paciente> pacienteOpt = repository.findById(cpf);
        if(!pacienteOpt.isPresent()){
            throw new RuntimeException("Paciente não cadastrado!");   
        }
        
        Paciente paciente = pacienteOpt.get();
            paciente.setAlergias(alergias);
            repository.save(paciente);
    }

	public Paciente obterPaciente(String cpf) {
		return repository.getOne(cpf);
	}
    
}
