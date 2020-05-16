package br.com.prontomed.peg.models;

import javax.persistence.Embeddable;

@Embeddable
public class RegistroMedico {

    private int numero;

    private String uf;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
