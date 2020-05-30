package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Medico;
import br.com.prontomed.peg.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    public Medico obterMedico(String cpf){
        return medicoRepository.getOne(cpf);
    }
}
