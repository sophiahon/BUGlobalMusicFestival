package com.a5am.team.buglobalmusicfestival.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 Created by pro on 2018/3/28.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Make the version of the DB if the database updated
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "myTest.db";
    public static final String TABLE_NAME = "Events";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    /**
     * First Running methon when the database is open
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    /**
     * First time create the database
     *
     * SQL sentence to create table with scheme.
     * Our scheme include:
     *      id primary key
     *      event
     *      artist
     *      place
     *      date
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE_NAME + " (id integer primary key, " +
                "event text, artist text, place text, date text)";
        db.execSQL(sql);
    }


    /**
     * Android will auto check the DB_VERSION.
     * If current version is newer than the last one
     * It will use this function to upgrade the database (migration)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop database and create again
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }



}
