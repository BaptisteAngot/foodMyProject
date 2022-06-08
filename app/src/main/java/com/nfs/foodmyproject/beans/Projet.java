package com.nfs.foodmyproject.beans;

import com.nfs.foodmyproject.mapping.ProjetMapping;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class Projet implements Serializable, ProjetMapping {

    private int id;
    private String titre;
    private String presentation;
    private Float montant_a_atteindre;
    private Float montant_actuel;
    private String photo_couverture;
    private ArrayList<String> photos;
    private ArrayList<Contrepartie> contreparties;
    private ArrayList<Don> dons;
    private String date_limite;

    public Projet(int id, String titre, String presentation, Float montant_a_atteindre, Float montant_actuel, String photo_couverture, ArrayList<String> photos, ArrayList<Contrepartie> contreparties, ArrayList<Don> dons, String date_limite) {
        this.id = id;
        this.titre = titre;
        this.presentation = presentation;
        this.montant_a_atteindre = montant_a_atteindre;
        this.montant_actuel = montant_actuel;
        this.photo_couverture = photo_couverture;
        this.photos = photos;
        this.contreparties = contreparties;
        this.dons = dons;
        this.date_limite = date_limite;
    }

    public Projet(int id, String titre, String presentation, Float montant_a_atteindre, Float montant_actuel, String date_limite){
        this.id = id;
        this.titre = titre;
        this.presentation = presentation;
        this.montant_a_atteindre = montant_a_atteindre;
        this.montant_actuel = montant_actuel;
        this.date_limite = date_limite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Float getMontant_a_atteindre() {
        return montant_a_atteindre;
    }

    public void setMontant_a_atteindre(Float montant_a_atteindre) {
        this.montant_a_atteindre = montant_a_atteindre;
    }

    public Float getMontant_actuel() {
        return montant_actuel;
    }

    public void setMontant_actuel(Float montant_actuel) {
        this.montant_actuel = montant_actuel;
    }

    public String getPhoto_couverture() {
        return photo_couverture;
    }

    public void setPhoto_couverture(String photo_couverture) {
        this.photo_couverture = photo_couverture;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<Contrepartie> getContreparties() {
        return contreparties;
    }

    public void setContreparties(ArrayList<Contrepartie> contreparties) {
        this.contreparties = contreparties;
    }

    public ArrayList<Don> getDons() {
        return dons;
    }

    public void setDons(ArrayList<Don> dons) {
        this.dons = dons;
    }

    public String getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(String date_limite) {
        this.date_limite = date_limite;
    }
}

