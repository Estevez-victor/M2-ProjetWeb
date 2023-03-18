package spring.devrep.sante.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dispos")
public class Dispo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    protected Pro pro;
    @Column(name = "jour", length = 150)
    protected String jour;
    @Column(name = "heure", length = 150)
    protected String heure;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "[ID=" + id + ", heure=" + heure + ", jour=" + jour + ", Medecin=" + pro.getNom() + "]";
    }

    public Dispo() {
    }

    public Dispo(Pro pro, String jour, String heure) {
        this.pro = pro;
        this.jour = jour;
        this.heure = heure;
    }

}
