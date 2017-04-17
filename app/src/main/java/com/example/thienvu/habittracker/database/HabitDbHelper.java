package com.example.thienvu.habittracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thienvu on 4/17/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "habit.db";

    public static final int DATABASE_VERSION = 1;

    /**
     *Constructor of HabitDbHelper
     * @param context
     */
    public HabitDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * CREATE DATABASE FOR THE FIRST TIME
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE "
                + HabitDatabase.HabitEntry.TABLE_NAME + "("
                + HabitDatabase.HabitEntry._ID_HABIT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitDatabase.HabitEntry.NAME_OF_ACTIVITY + " TEXT NOT NULL, "
                + HabitDatabase.HabitEntry.TIME_OF_ACTIVITY + " INTEGER, "
                + HabitDatabase.HabitEntry.CALORIES_GAIN + " INTEGER, "
                + HabitDatabase.HabitEntry.CALORIES_LOSS + " INTEGER);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
