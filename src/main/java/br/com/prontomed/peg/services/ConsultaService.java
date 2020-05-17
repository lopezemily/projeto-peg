package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Consulta;
import br.com.prontomed.peg.repositories.ConsultaRepository;
import br.com.prontomed.peg.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    //@Autowired
    //private UnidadeRepository unidadeRepository;
    
    public void criarConsulta(Consulta consulta){
        //Optional<Unidade> unidade = unidadeRepository.findById(consulta.getUnidade())
        consultaRepository.save(consulta);
    }
}
