package br.com.prontomed.peg.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unidade {

    @Id
    private String cnpj;
    
    private String nome;
    
    @Embedded
    private Endereco endereco;
    
    @Embedded
    private Contato contato;

}
