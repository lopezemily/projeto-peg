package br.com.prontomed.peg.models;

import java.util.List;

public class Paciente extends Pessoa {

    private List<String> alergias;
    private boolean dm;
    private boolean has;
    private boolean ic;
    private boolean dpoc;
    private boolean ca;
    private boolean etilista;
    private boolean tabagista;

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public boolean isDm() {
        return dm;
    }

    public void setDm(boolean dm) {
        this.dm = dm;
    }

    public boolean isHas() {
        return has;
    }

    public void setHas(boolean has) {
        this.has = has;
    }

    public boolean isIc() {
        return ic;
    }

    public void setIc(boolean ic) {
        this.ic = ic;
    }

    public boolean isDpoc() {
        return dpoc;
    }

    public void setDpoc(boolean dpoc) {
        this.dpoc = dpoc;
    }

    public boolean isCa() {
        return ca;
    }

    public void setCa(boolean ca) {
        this.ca = ca;
    }

    public boolean isEtilista() {
        return etilista;
    }

    public void setEtilista(boolean etilista) {
        this.etilista = etilista;
    }

    public boolean isTabagista() {
        return tabagista;
    }

    public void setTabagista(boolean tabagista) {
        this.tabagista = tabagista;
    }
    
    
}
