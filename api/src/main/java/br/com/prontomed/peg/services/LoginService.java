package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Credencial;
import br.com.prontomed.peg.repositories.LoginRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginrepository;
    
    public boolean fazerLogin(Credencial credencial){
        Optional<Credencial> credencialBanco = loginrepository.findById(credencial.getLogin());
        
        if(credencialBanco.isPresent()){
            if(credencialBanco.get().getSenha().equals(credencial.getSenha())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
