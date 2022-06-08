package com.nfs.foodmyproject.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nfs.foodmyproject.helper.DBHelper;

public class BaseDaoSqlite {

        protected DBHelper dbHelper;
        protected SQLiteDatabase db;

        public BaseDaoSqlite(Context context){
            dbHelper = new DBHelper(context);
        }

        protected SQLiteDatabase getDB() {
            if (db == null || !db.isOpen()) {
                db = dbHelper.getWritableDatabase();
            }
            return db;
        }
}
