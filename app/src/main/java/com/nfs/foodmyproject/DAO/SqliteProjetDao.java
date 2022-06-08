package com.nfs.foodmyproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.nfs.foodmyproject.beans.Projet;
import com.nfs.foodmyproject.helper.TagLog;

import java.util.ArrayList;
import java.util.List;

public class SqliteProjetDao extends BaseDaoSqlite implements ProjetDao{

    public SqliteProjetDao(Context context){
        super(context);
    }


    @Override
    public List<Projet> getAll() {
        ArrayList<Projet> list = new ArrayList<Projet>();
        String[] cols = {Projet.ID, Projet.TITRE,Projet.PRESENTATION, Projet.MONTANT_A_ATTEINDRE, Projet.MONTANT_ACTUEL, Projet.DATE_LIMITE};
        Log.d(TagLog.sql, "projet get all");
        Cursor cursor = getDB().query(Projet.TABLE, cols, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Projet projet = new Projet((int)cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4), cursor.getString(5));
            list.add(projet);
            cursor.moveToNext();
        }
        cursor.close();     // obligatoire
        getDB().close();    // performance : on est dans une application qui ne vas pas taper dans le base de manière intensivement
        // puis retourner la liste de quotes
        return list;
    }

    @Override
    public Projet getProjet(int index) {
        Projet res = null;
        String[] cols = {Projet.ID, Projet.TITRE, Projet.PRESENTATION, Projet.MONTANT_A_ATTEINDRE, Projet.MONTANT_ACTUEL, Projet.DATE_LIMITE};
        String[] params = { ""+index };
        Cursor cursor = getDB().query(Projet.TABLE, cols, "id=?", params, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {  // meilleur qu'un try catch
            res = new Projet((int)cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4), cursor.getString(5));
            cursor.moveToNext();
        }
        cursor.close();
        getDB().close();
        return res;
    }

    @Override
    public void update(Projet projet) {
        ContentValues values = mapFromQuote(projet);
        String[] params = { ""+projet.getId() };
        getDB().update(Projet.TABLE, values, Projet.ID + " = ?", params);
        getDB().close();
    }

    @Override
    public int addProjet(Projet projet) {
        ContentValues values = mapFromQuote(projet);
        int id = (int) getDB().insert(Projet.TABLE, null, values);
        getDB().close();
        return id;
    }

    private ContentValues mapFromQuote(Projet projet){
        ContentValues values = new ContentValues();
        values.put(Projet.TITRE, (projet.getTitre()));
        values.put(Projet.PRESENTATION, (projet.getPresentation()));
        values.put(Projet.MONTANT_A_ATTEINDRE, (projet.getMontant_a_atteindre()));
        values.put(Projet.MONTANT_ACTUEL, (projet.getMontant_actuel()));
        values.put(Projet.DATE_LIMITE, (projet.getDate_limite()));
        return values;
    }
}
