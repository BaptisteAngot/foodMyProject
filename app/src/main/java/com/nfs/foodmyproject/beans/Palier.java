package com.nfs.foodmyproject.beans;

public class Palier {
    private String title;
    private String description;
    private double price;

    public Palier(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Palier{" + "title=" + title + ", description=" + description + ", price=" + price + '}';
    }
}
