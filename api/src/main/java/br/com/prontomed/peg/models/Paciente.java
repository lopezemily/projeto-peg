package br.com.prontomed.peg.models;

import java.util.List;

public class Paciente extends Pessoa {

    private List<String> alergias;
    private List<String> cirurgias;

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public List<String> getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(List<String> cirurgias) {
        this.cirurgias = cirurgias;
    }
    
    
}
