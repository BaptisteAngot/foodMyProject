package com.nfs.foodmyproject.beans;

import java.io.Serializable;

public class Don implements Serializable {

    private int id;
    private Float montant;

    public Don(int id, Float montant) {
        this.id = id;
        this.montant = montant;
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
}