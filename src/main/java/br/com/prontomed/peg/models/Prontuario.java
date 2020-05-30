package br.com.prontomed.peg.models;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prontuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String queixa;
    
    @OneToMany
    private List<CID> cids;
    
    private String pa;
    
    private float temperatura;
    
    private float fr;
    
    //private String medicamentos;
    
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

    public List<CID> getCids() {
        return cids;
    }

    public void setCids(List<CID> cids) {
        this.cids = cids;
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
    
    

}
