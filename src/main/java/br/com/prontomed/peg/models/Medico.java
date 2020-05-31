package br.com.prontomed.peg.models;

import java.time.DayOfWeek;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Medico extends Pessoa {
    
    @Embedded
    private RegistroMedico crm;
    
    @ElementCollection(targetClass=DayOfWeek.class)
    private List<DayOfWeek> disponibilidade;
    
    @ManyToMany
    private List<Especialidade> especialidades;

    public RegistroMedico getCrm() {
        return crm;
    }

    public void setCrm(RegistroMedico crm) {
        this.crm = crm;
    }

    public List<DayOfWeek> getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(List<DayOfWeek> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
    
}
