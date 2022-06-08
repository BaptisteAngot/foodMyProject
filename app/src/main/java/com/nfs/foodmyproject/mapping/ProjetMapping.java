package com.nfs.foodmyproject.mapping;

public interface ProjetMapping extends BaseMapping{


    public static final String TITRE = "titre";
    public static final String PRESENTATION = "presentation";
    public static final String MONTANT_A_ATTEINDRE = "montant_a_atteindre";
    public static final String MONTANT_ACTUEL = "montant_actuel";
    public static final String DATE_LIMITE = "date_limite";


    public static final String TABLE = "projets";

    public static final String DML_CREATE = "CREATE TABLE "+ TABLE +
            "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITRE + " TEXT NOT NULL, " +
            PRESENTATION + " TEXT NOT NULL, " +
            MONTANT_A_ATTEINDRE + " REAL NOT NULL, " +
            MONTANT_ACTUEL + " REAL NOT NULL, " +
            DATE_LIMITE + " NUMERIC NOT NULL);";
}
