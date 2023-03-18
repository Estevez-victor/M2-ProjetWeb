package spring.devrep.sante.entities;

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
@Table(name = "rdvs")
public class RDV {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    protected Pro pro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    protected Pro patient;
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

    public Pro getPatient() {
        return patient;
    }

    public void setPatient(Pro patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "[ID=" + id + ", heure=" + heure + ", jour=" + jour + ", Medecin=" + pro.getNom() + ", Patient="
                + patient.getNom() + "]";
    }

    public RDV() {
    }

    public RDV(Pro pro, Pro patient, String jour, String heure) {
        this.pro = pro;
        this.patient = patient;
        this.jour = jour;
        this.heure = heure;
    }

}
