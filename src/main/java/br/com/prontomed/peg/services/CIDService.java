package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.CID;
import br.com.prontomed.peg.repositories.CIDRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CIDService {
    
    @Autowired
    private CIDRepository cidRepository;
    
    public List<CID> obterTodosCids(){
        return cidRepository.findAll();
    }
    
    public Optional<CID> obterPorId(String id){
        return cidRepository.findById(id);
    }
}
