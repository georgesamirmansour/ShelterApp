package com.example.gorgesamir.shelterapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gorgesamir.shelterapp.data.PetContract;
import com.example.gorgesamir.shelterapp.data.PetsDbHelper;

public class CatalogActivity extends AppCompatActivity {

    PetsDbHelper petsDbHelper = new PetsDbHelper(this);
    PetContract.PetEntry entry;

    @Override
    protected void onStart() {
        super.onStart();
        displayDataBaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        displayDataBaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void displayDataBaseInfo() {
        SQLiteDatabase database = petsDbHelper.getReadableDatabase();
        String[] projection = {
                entry._ID,
                entry.COLUMN_NAME,
                entry.COLUMN_BREED,
                entry.COLUMN_GENDER,
                entry.COLUMN_WEIGHT
        };
        Cursor cursor = database.query(
                PetContract.PetEntry.TABLE_NAME
                , projection
                , null
                , null
                , null
                , null
                , null
        );
        TextView displayTextView = (TextView) findViewById(R.id.text_view_pet);
        try {
            displayTextView.setText("Number of rows in DB is " + cursor.getCount() + "\n");
            displayTextView.append(PetContract.PetEntry._ID + " - " +
                    PetContract.PetEntry.COLUMN_NAME + " - " +
                    PetContract.PetEntry.COLUMN_BREED + " - " +
                    PetContract.PetEntry.COLUMN_GENDER + " - "
                    + PetContract.PetEntry.COLUMN_WEIGHT + " - " + "\n"
            );

            int idColumnIndex = cursor.getColumnIndex(entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(entry.COLUMN_NAME);
            int breedColumnIndex = cursor.getColumnIndex(entry.COLUMN_BREED);
            int genderColumnIndex = cursor.getColumnIndex(entry.COLUMN_GENDER);
            int weightCurrentIndex = cursor.getColumnIndex(entry.COLUMN_WEIGHT);

            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightCurrentIndex);

                displayTextView.append("\n"
                        + currentID + " - "
                        + currentName + " - "
                        + currentBreed + " - "
                        + currentGender + " - "
                        + currentWeight + " - ");
                if (cursor.equals(null)) {
                    break;
                }
            }
        } finally {
            cursor.close();
        }
    }
}
