package com.example.gorgesamir.shelterapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gorge samir on 2017-12-19.
 */

public class PetsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = " shelter.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = " ,";
    private static final String NOT_NULL = " NOT NULL";
    private static final String DEFAULT_0 = " DEFAULT 0";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE" + PetContract.PetEntry.TABLE_NAME +
            " ( " + PetContract.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            PetContract.PetEntry.COLUMN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
            PetContract.PetEntry.COLUMN_BREED + TEXT_TYPE + COMMA_SEP +
            PetContract.PetEntry.COLUMN_GENDER + INTEGER_TYPE+ NOT_NULL + COMMA_SEP +
            PetContract.PetEntry.COLUMN_WEIGHT + INTEGER_TYPE + NOT_NULL + DEFAULT_0 + " ); ";

    private static final String SQL_DELETE_ENTRIES = " DROP TABLE IF EXIST " + PetContract.PetEntry.TABLE_NAME;

    public PetsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
