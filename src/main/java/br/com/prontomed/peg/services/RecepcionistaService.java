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
}
