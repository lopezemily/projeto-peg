package br.com.prontomed.peg.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "cpf"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Funcao> roles;

    public Usuario(){
        
    }

    public Usuario(String cpf, String senha, Set<Funcao> roles) {
        this.cpf = cpf;
        this.senha = senha;
        this.roles = roles;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Funcao> getRoles() {
        return roles;
    }

    public void setRoles(Set<Funcao> roles) {
        this.roles = roles;
    }
    
}