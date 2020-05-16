package br.com.prontomed.peg.models;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Medico extends Pessoa {
    
    @Embedded
    private RegistroMedico crm;
    
    @Embedded
    private Disponibilidade disponibilidade;
    
    @OneToMany
    private List<Especialidade> especialidades;

    public RegistroMedico getCrm() {
        return crm;
    }

    public void setCrm(RegistroMedico crm) {
        this.crm = crm;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
    
    
}
