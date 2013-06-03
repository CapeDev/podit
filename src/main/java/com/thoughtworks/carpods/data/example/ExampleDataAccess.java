package com.thoughtworks.carpods.data.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.thoughtworks.carpods.data.PodItDatabase;

public class ExampleDataAccess {

    private static final String CLASS_TAG = "ExampleDataAccess";

    private SQLiteDatabase database;
    private final PodItDatabase podItDatabase;

    public ExampleDataAccess(Context context) {
        podItDatabase = PodItDatabase.getInstance(context);
    }

    public void saveStringExample(String personsName) {
        String table = PodItDatabase.EXAMPLE_TABLE;

        ContentValues info = new ContentValues();
        info.put(PodItDatabase.EXAMPLE_STRING, personsName);
        database = podItDatabase.getWritableDatabase();
        database.insert(table, null, info);
        database.close();
    }

    public String getExampleString() {
        String exampleString = "";
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.EXAMPLE_STRING};
        String whereClause = PodItDatabase.ROWID + " > ?";
        String[] whereArgs = {"0"};

        database = podItDatabase.getWritableDatabase();
        try {
            cursor = this.database.query(PodItDatabase.EXAMPLE_TABLE, columns, whereClause, whereArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                exampleString = cursor.getString(cursor.getColumnIndex(PodItDatabase.EXAMPLE_STRING));
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return exampleString;
    }
}