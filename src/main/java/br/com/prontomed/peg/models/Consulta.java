package br.com.prontomed.peg.models;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long numAtendimento;
    
    private String tipoAtendimento;
    
    private String convenio;
    
    @ManyToOne
    private Medico medico;
    
    @ManyToOne
    private Paciente paciente;
    
    @ManyToOne
    private Unidade unidade;
    
    private LocalDateTime dtHrConsulta;
    
    @ManyToOne
    private Especialidade especialidade;
    
    @Embedded
    private Receita receita;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Prontuario prontuario;
    
    private boolean realizada;
    
    private boolean confirmada;

    public long getNumAtendimento() {
        return numAtendimento;
    }

    public void setNumAtendimento(long numAtendimento) {
        this.numAtendimento = numAtendimento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public LocalDateTime getDtHrConsulta() {
        return dtHrConsulta;
    }

    public void setDtHrConsulta(LocalDateTime dtHrConsulta) {
        this.dtHrConsulta = dtHrConsulta;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }    
}
