package br.com.prontomed.peg.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CID {

    @Id
    private String codigo;
    
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CID(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public CID() { }
    
        public CID(String codigo) {
        this.codigo = codigo;
    }
    
}
