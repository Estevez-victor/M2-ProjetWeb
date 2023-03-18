package spring.devrep.sante.entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.util.Pair;


@Entity
@Table(name = "pros")
public class Pro extends Person {

    @Column(name = "metier", length = 150)
    protected String metier;
    @Column(name = "adresse", length = 150)
    protected String adresse;
    @Column(name = "horaire", length = 150)
    protected String horaire;
    @Column(name = "tel", length = 150)
    protected String tel;
    @Column(name = "visitor", length = 150)
    protected boolean visitor;
    @Transient
    private ArrayList<Pair<String, String>> dispo;



    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }

    public ArrayList<Pair<String, String>> getDispo() {
        return dispo;
    }

    public Pro() {
        super();
    }

    public Pro(String nom, String prenom, String mail, String mdp, boolean visitor) {
        super(nom, prenom, mail, mdp);
        this.visitor = visitor;
        this.dispo=new ArrayList<Pair<String,String>>();
    }

    @Override
    public String toString() {
        return "[ID=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + "]";
    }

}