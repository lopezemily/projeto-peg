package br.com.prontomed.peg.models;

import javax.persistence.Embeddable;

@Embeddable
public class RegistroMedico {

    private int numeroRegistro;

    private String uf;

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
