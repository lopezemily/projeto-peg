package br.com.prontomed.peg.models;

import javax.persistence.Embeddable;

@Embeddable
public class Contato {
    
    private String telefone;
    
    private String celular;
    
    private String telEmergencia;
    
    private String email;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelEmergencia() {
        return telEmergencia;
    }

    public void setTelEmergencia(String telEmergencia) {
        this.telEmergencia = telEmergencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
