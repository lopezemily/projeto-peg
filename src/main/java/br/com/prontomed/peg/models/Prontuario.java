package br.com.prontomed.peg.models;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String queixa;

    @ManyToOne
    private CID cid;

    private String pa;

    private float temperatura;

    private float fr;

    private float peso;

    private float fc;

    public float getFc() {
        return fc;
    }

    public void setFc(float fc) {
        this.fc = fc;
    }
    
    private String avalMedico;

    @ElementCollection
    private List<String> exames;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getFr() {
        return fr;
    }

    public void setFr(float fr) {
        this.fr = fr;
    }

//    public String getMedicamentos() {
//        return medicamentos;
//    }
//
//    public void setMedicamentos(String medicamentos) {
//        this.medicamentos = medicamentos;
//    }
    public String getAvalMedico() {
        return avalMedico;
    }

    public void setAvalMedico(String avalMedico) {
        this.avalMedico = avalMedico;
    }

    public List<String> getExames() {
        return exames;
    }

    public void setExames(List<String> exames) {
        this.exames = exames;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public CID getCid() {
        return cid;
    }

    public void setCid(CID cid) {
        this.cid = cid;
    }
}
