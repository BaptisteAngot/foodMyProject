package com.nfs.foodmyproject.beans;

import java.io.Serializable;

public class Contrepartie implements Serializable{

    private int id;
    private Float montant;
    private String titre;
    private String description;

    public Contrepartie(int id, Float montant, String titre, String description) {
        this.id = id;
        this.montant = montant;
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
