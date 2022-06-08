package com.nfs.foodmyproject.DAO;

import com.nfs.foodmyproject.beans.Projet;

import java.util.List;

public interface ProjetDao {

    public List<Projet> getAll();
    public Projet getProjet(int index);
    public void update(Projet projet);
    public int addProjet(Projet projet);

}
