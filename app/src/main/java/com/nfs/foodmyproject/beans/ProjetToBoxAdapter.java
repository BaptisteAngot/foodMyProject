package com.nfs.foodmyproject.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProjetToBoxAdapter {

    List<Box> boxes;
    public ProjetToBoxAdapter(){
        boxes = new ArrayList<Box>();
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    public List<Box> ConvertProjetToBox(List<Projet> projets){
        for(Projet projet : projets){
            this.boxes.add(new Box( projet.getTitre(), projet.getPresentation(), projet.getPhoto_couverture(), ((int)(projet.getMontant_actuel()/projet.getMontant_a_atteindre()*100)), LocalDate.parse(projet.getDate_limite(), formatter)));
        }
        return this.boxes;
    }

}
