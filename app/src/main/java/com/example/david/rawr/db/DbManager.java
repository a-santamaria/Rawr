package com.example.david.rawr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    public static String NOM_TABLE_USERS= "users";
    public static String NOM_TABLE_PETS= "pets";

    public static String NOM_COLUM_USER = "username";
    public static String NOM_COLUM_PASS = "password";
    public static String NOM_COLUM_PICTURE = "picture";
    public static String NOM_COLUM_NAME = "name";
    public static String NOM_COLUM_LASTNAME = "lastname";

    public static String NOM_COLUMN_PETUSER = "petuser";
    public static String NOM_COLUMN_PICTURE = "picture";
    public static String NOM_COLUMN_TYPE = "type";

    public static int INDEX_USER = 0;
    public static int INDEX_PASS = 1;

    String query_users = "CREATE TABLE "+NOM_TABLE_USERS+"("+
            NOM_COLUM_USER+" TEXT PRIMARY KEY,"+
            NOM_COLUM_PASS+" TEXT,"+
            NOM_COLUM_PICTURE+" BLOB,"+
            NOM_COLUM_NAME+" TEXT,"+
            NOM_COLUM_LASTNAME+" TEXT"+
            ")";

    String query_pets = "CREATE TABLE " + NOM_TABLE_PETS + "("+
            NOM_COLUMN_PETUSER+" TEXT PRIMARY KEY,"+
            NOM_COLUM_PICTURE+ " BLOB,"+
            NOM_COLUMN_TYPE+" TEXT,"+
            NOM_COLUM_USER+ "TEXT REFERENCES " + NOM_TABLE_USERS + "("+NOM_COLUM_USER+")"+
            ")";

    public DbManager(Context context) {
        super(context,"rawr", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(query_users);
        db.execSQL(query_pets);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE_PETS);
            db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE_USERS);
        }
        db.execSQL(query_users);
        db.execSQL(query_pets);
    }
}