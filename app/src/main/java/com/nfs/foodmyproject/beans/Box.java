package com.nfs.foodmyproject.beans;

import java.time.LocalDate;

public class Box {
    private int id;
    private String title;
    private String description;
    private String image;
    private int percentage;
    private LocalDate dateEnd;
    private Double priceCollect;
    private int nbContributeur;


    public Box(int id,String title, String description, String image, int percentage, LocalDate dateEnd, Double priceCollect, int nbContributeur) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.percentage = percentage;
        this.dateEnd = dateEnd;
        this.priceCollect = priceCollect;
        this.nbContributeur = nbContributeur;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getPriceCollect() {
        return priceCollect;
    }

    public void setPriceCollect(Double priceCollect) {
        this.priceCollect = priceCollect;
    }

    public int getNbContributeur() {
        return nbContributeur;
    }

    public void setNbContributeur(int nbContributeur) {
        this.nbContributeur = nbContributeur;
    }

    public int getId() {
        return id;
    }
}
