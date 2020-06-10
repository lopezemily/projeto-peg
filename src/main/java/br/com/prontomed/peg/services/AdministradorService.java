package br.com.prontomed.peg.services;

import br.com.prontomed.peg.models.Administrador;
import br.com.prontomed.peg.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    public Administrador obterAdmin(String adminId) {
        return administradorRepository.getOne(adminId);
    }
    
    public void atualizarAdmin(String cpf, Administrador admin) {
        admin.setCpf(cpf);
        administradorRepository.save(admin);
    }
}
