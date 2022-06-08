package com.nfs.foodmyproject.DAO;

import android.content.Context;

public class DaoFactory {
    public static ProjetDao projetDao;

    private DaoFactory(){}

    public static ProjetDao getProjetDao(Context context) {
        if(projetDao == null){
            projetDao = new SqliteProjetDao(context);
        }
        return projetDao;
    }
}
