package br.com.prontomed.peg.dto;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import br.com.prontomed.peg.models.Medico;

public class CadastroMedico extends Medico {

    private String senha;

    private boolean segunda;
    private boolean terca;
    private boolean quarta;
    private boolean quinta;
    private boolean sexta;
    private boolean sabado;
    private boolean domingo;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean isSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean isQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean isQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean isTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean isSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public List<DayOfWeek> getDaysOfWeek(){
        List<DayOfWeek> days = new ArrayList<>();
        
        if(segunda) days.add(DayOfWeek.MONDAY);
        if(terca) days.add(DayOfWeek.TUESDAY);
        if(quarta) days.add(DayOfWeek.WEDNESDAY);
        if(quinta) days.add(DayOfWeek.THURSDAY);
        if(sexta) days.add(DayOfWeek.FRIDAY);
        if(sabado) days.add(DayOfWeek.SATURDAY);
        if(domingo) days.add(DayOfWeek.SUNDAY);

        return days;
    }
}