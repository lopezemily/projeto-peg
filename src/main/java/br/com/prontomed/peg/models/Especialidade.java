package br.com.prontomed.peg.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Especialidade {

    @Id
    private long id;
    
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
