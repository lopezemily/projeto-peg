package br.com.prontomed.peg.models;

import javax.persistence.Embeddable;

@Embeddable
public class Receita {

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
