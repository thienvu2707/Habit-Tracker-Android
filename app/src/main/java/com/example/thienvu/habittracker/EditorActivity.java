package com.example.thienvu.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thienvu.habittracker.database.HabitDatabase;
import com.example.thienvu.habittracker.database.HabitDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mActivityEditText;
    private EditText mTimeEditText;
    private EditText mGainEditText;
    private EditText mLossEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //find all edit in editor field
        mActivityEditText = (EditText) findViewById(R.id.edit_habit_name);
        mTimeEditText = (EditText) findViewById(R.id.edit_habit_time);
        mGainEditText = (EditText) findViewById(R.id.edit_calories_gain);
        mLossEditText = (EditText) findViewById(R.id.edit_calories_loss);
    }

    private void insertHabit() {
        //get text from all the edit Text
        String editActivity = mActivityEditText.getText().toString();

        String editTime = mTimeEditText.getText().toString();
        int changeTimeToInteger = Integer.parseInt(editTime);

        String editGain = mGainEditText.getText().toString();
        int changeGainToInteger = Integer.parseInt(editGain);

        String editLoss = mLossEditText.getText().toString();
        int changeLossToInteger = Integer.parseInt(editLoss);

        //Call HabitDbHelper to access to database
        HabitDbHelper helper = new HabitDbHelper(this);

        //get database to write mode
        SQLiteDatabase db = helper.getWritableDatabase();

        //Set contentValue where column are the key
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitDatabase.HabitEntry.NAME_OF_ACTIVITY, editActivity);
        contentValues.put(HabitDatabase.HabitEntry.TIME_OF_ACTIVITY, changeTimeToInteger);
        contentValues.put(HabitDatabase.HabitEntry.CALORIES_GAIN, changeGainToInteger);
        contentValues.put(HabitDatabase.HabitEntry.CALORIES_LOSS, changeLossToInteger);

        //insert into database
        long newRowId = db.insert(HabitDatabase.HabitEntry.TABLE_NAME, null, contentValues);

        //check if data is saved to database
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving Habit in database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Save Habit successfully", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Create a menu option to press
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu option from the res/menu
        //This add menu item to the app bar
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * action when pressed on menu items
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //user clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            //Response to a click on "SAVE"
            case R.id.action_save:
                //save what edit
                insertHabit();
                //finish it to save
                finish();
                return true;
            //Response to delete button
            case R.id.action_delete:
                //do nothing yet
                return true;
            //Response to a click UP arrow
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
