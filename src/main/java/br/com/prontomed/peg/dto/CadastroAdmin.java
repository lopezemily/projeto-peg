package br.com.prontomed.peg.dto;

import br.com.prontomed.peg.models.Administrador;

public class CadastroAdmin extends Administrador {
    
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
