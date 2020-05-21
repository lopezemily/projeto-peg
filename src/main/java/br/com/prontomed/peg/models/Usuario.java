package br.com.prontomed.peg.models;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    //@Length(min = 5, message = "*Your user name must have at least 5 characters")
    //@NotEmpty(message = "*Please provide a user name")
    private String userName;
    
    @Column(name = "email")
    //@Email(message = "*Please provide a valid Email")
    //@NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "name")
    //@NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    //@NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Funcao> roles;

    public Usuario(){
        
    }

    public Usuario(int id, String userName, String email, String password, String name, String lastName, Boolean active, Set<Funcao> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Funcao> getRoles() {
        return roles;
    }

    public void setRoles(Set<Funcao> roles) {
        this.roles = roles;
    }

    
}