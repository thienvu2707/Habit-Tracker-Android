package com.example.thienvu.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.thienvu.habittracker.database.HabitDatabase;
import com.example.thienvu.habittracker.database.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * onStart to refresh UI if something change in the database
     */
    @Override
    protected void onStart() {
        super.onStart();
        displayInfo();
    }

    private Cursor displayInfo() {
        HabitDbHelper helper = new HabitDbHelper(this);

        SQLiteDatabase database = helper.getReadableDatabase();

//        Cursor cursor = database.rawQuery("SELECT * FROM " + HabitDatabase.HabitEntry.TABLE_NAME, null);

        //projection this is to select column that needed to show
        String[] projection = {
                HabitDatabase.HabitEntry._ID_HABIT,
                HabitDatabase.HabitEntry.NAME_OF_ACTIVITY,
                HabitDatabase.HabitEntry.TIME_OF_ACTIVITY,
                HabitDatabase.HabitEntry.CALORIES_GAIN,
                HabitDatabase.HabitEntry.CALORIES_LOSS
        };

        //This is Select ... from table_name
        Cursor cursor = database.query(HabitDatabase.HabitEntry.TABLE_NAME, projection, null, null, null, null, null, null);

        try {
            TextView viewText = (TextView) findViewById(R.id.text_view_habit);
            viewText.setText("Number of row: " + cursor.getCount() + "\n\n");

            viewText.append(HabitDatabase.HabitEntry._ID_HABIT + " - " +
                    HabitDatabase.HabitEntry.NAME_OF_ACTIVITY + " - " +
                    HabitDatabase.HabitEntry.TIME_OF_ACTIVITY + " - " +
                    HabitDatabase.HabitEntry.CALORIES_GAIN + " - " +
                    HabitDatabase.HabitEntry.CALORIES_LOSS);

            //figure which index in which column
            int idColumnIndex = cursor.getColumnIndex(HabitDatabase.HabitEntry._ID_HABIT);
            int nameColumnIndex = cursor.getColumnIndex(HabitDatabase.HabitEntry.NAME_OF_ACTIVITY);
            int timeColumnIndex = cursor.getColumnIndex(HabitDatabase.HabitEntry.TIME_OF_ACTIVITY);
            int gainColumnIndex = cursor.getColumnIndex(HabitDatabase.HabitEntry.CALORIES_GAIN);
            int lossColumnIndex = cursor.getColumnIndex(HabitDatabase.HabitEntry.CALORIES_LOSS);

            //move the cursor
            while (cursor.moveToNext()) {
                //read the column
                int currentId = cursor.getInt(idColumnIndex);
                String currentActivity = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                int currentGain = cursor.getInt(gainColumnIndex);
                int currentLoss = cursor.getInt(lossColumnIndex);

                viewText.append("\n" + currentId + " - "
                        + currentActivity + " - "
                        + currentTime + " - "
                        + currentGain + " - "
                        + currentLoss);
            }

        } finally {
            cursor.close();
        }
        return cursor;
    }
}
