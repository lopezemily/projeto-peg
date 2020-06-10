package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Recepcionista;
import br.com.prontomed.peg.repositories.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService {
    
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    
    public Recepcionista obterRecepcionista(String recepcionistaId) {
        return recepcionistaRepository.getOne(recepcionistaId);
    }
    
    public void inserirRecepcionista(Recepcionista recepcionista){
        if(recepcionistaRepository.existsById(recepcionista.getCpf())){
            throw new RuntimeException("Recepcionista já cadastrado!");
        }
        
        recepcionistaRepository.save(recepcionista);
    }
    
    public void atualizarRecepcionista(Recepcionista recepcionista){
        if(recepcionistaRepository.existsById(recepcionista.getCpf())){
            recepcionistaRepository.save(recepcionista);
        } else {
            throw new RuntimeException("Paciente não cadastrado!");
        }
    }

    public void atualizarRecepcionista(String cpf, Recepcionista recepcionista) {
        recepcionista.setCpf(cpf);
        recepcionistaRepository.save(recepcionista);
    }
}
