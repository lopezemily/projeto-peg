package br.com.prontomed.peg.dto;

import java.time.LocalTime;

public class Disponibilidade {

    private String idMedico;
    private String nomeMedico;
    private LocalTime horario;

    public String getIdMedico() {
        return idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Disponibilidade(String idMedico, String nomeMedico, LocalTime horario) {
        this.idMedico = idMedico;
        this.nomeMedico = nomeMedico;
        this.horario = horario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Disponibilidade) {
            Disponibilidade d = (Disponibilidade) obj;
            return d.horario.compareTo(this.horario) == 0 && d.idMedico.equals(this.idMedico);
        }
        return false;
    }
    
}