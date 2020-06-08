package br.com.prontomed.peg.dto;

import br.com.prontomed.peg.models.Recepcionista;

public class CadastroRecepcionista extends Recepcionista{
    
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
