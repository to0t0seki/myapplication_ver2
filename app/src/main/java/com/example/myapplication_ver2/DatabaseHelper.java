package com.example.myapplication_ver2;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("createDB");
        sqLiteDatabase.execSQL("create table minoasamachine (name,URL)");


                /*
                "create table minoasatable ("+
                        "date,machineNo,machineName,medal,BB,RB,totalGames,last,"+
                "primary key(date,machineNO))");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("UpdateDB");

    }
}