package com.example.thienvu.habittracker.database;

import android.provider.BaseColumns;

/**
 * Created by thienvu on 4/17/17.
 */

public class HabitDatabase {
    /**
     * Private contact class to prevent accident
     * Private constructor
     */
    private HabitDatabase(){}

    public static final class HabitEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "habit";

        //Name of each column
        public static final String _ID_HABIT = "_id";
        public static final String NAME_OF_ACTIVITY = "name";
        public static final String TIME_OF_ACTIVITY = "time";
        public static final String CALORIES_GAIN = "gain";
        public static final String CALORIES_LOSS = "loss";

    }
}
