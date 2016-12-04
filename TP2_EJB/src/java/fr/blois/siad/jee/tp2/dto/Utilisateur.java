package fr.blois.siad.jee.tp2.dto;

import java.io.Serializable;
import java.util.Date;

public class Utilisateur implements Serializable {

    private Integer id;
    private String email;
    private String motDePasse;
    private String nom;
    private Date dateInscription;
    private Boolean banni;

    public Utilisateur() {
    }

    public Utilisateur(Integer id, String email, String motDePasse, String nom, Date dateInscription, Boolean banni) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.dateInscription = dateInscription;
        this.banni = banni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Boolean getBanni() {
        return banni;
    }

    public void setBanni(Boolean banni) {
        this.banni = banni;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", motDePasse=" + motDePasse + ", nom=" + nom + ", dateInscription=" + dateInscription + ", banni=" + banni + '}';
    }
}
