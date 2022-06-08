package com.nfs.foodmyproject.beans;
import java.io.Serializable;

public class Utilisateur {

    private int id;
    private String email;
    private String pseudo;
    private String photo;

    public Utilisateur(int id, String email, String pseudo, String photo) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}


