package br.com.prontomed.peg.dto;

import br.com.prontomed.peg.models.Paciente;

public class CadastroPaciente extends Paciente{
    
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
