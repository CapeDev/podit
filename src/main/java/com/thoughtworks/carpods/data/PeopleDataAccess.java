package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PeopleDataAccess {

    private static final String CLASS_TAG = "PeopleDataAccess";

    private SQLiteDatabase database;
    private final PodItDatabase podItDatabase;

    public PeopleDataAccess(Context context) {
        podItDatabase = PodItDatabase.getInstance(context);
    }

    public void savePerson(Person person) {
        String table = PodItDatabase.PERSON_TABLE;

        ContentValues info = new ContentValues();
        info.put(PodItDatabase.FIRST_NAME, person.getFirstName());
        info.put(PodItDatabase.LAST_NAME, person.getLastName());
        info.put(PodItDatabase.HOME_LOCATION, person.getHomeLocation());
        info.put(PodItDatabase.ABOUT_ME, person.getAboutMe());

        database = podItDatabase.getWritableDatabase();
        database.insert(table, null, info);
        database.close();

        Log.d(CLASS_TAG, "saved " + person.getFirstName() + " to the local database");
    }

    public Person getFirstPersonFromDatabase() {
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.FIRST_NAME, PodItDatabase.LAST_NAME, PodItDatabase.HOME_LOCATION, PodItDatabase.ABOUT_ME};
        String selection = PodItDatabase.ROWID + " = ?";
        String[] selectionArgs = {"1"};

        Person.Builder personBuilder = new Person.Builder();

        database = podItDatabase.getWritableDatabase();
        try {
            cursor = database.query(PodItDatabase.PERSON_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                personBuilder.firstName(cursor.getString(cursor.getColumnIndex(PodItDatabase.FIRST_NAME)));
                personBuilder.lastName(cursor.getString(cursor.getColumnIndex(PodItDatabase.LAST_NAME)));
                personBuilder.homeLocation(cursor.getString(cursor.getColumnIndex(PodItDatabase.HOME_LOCATION)));
                personBuilder.aboutMe(cursor.getString(cursor.getColumnIndex(PodItDatabase.ABOUT_ME)));
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }

        return personBuilder.build();
    }

    public List<Person> getAllPeopleNames() {
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.FIRST_NAME, PodItDatabase.LAST_NAME};
        String selection = null;
        String[] selectionArgs = null;

        List<Person> peopleList = new ArrayList<Person>();

        database = podItDatabase.getWritableDatabase();
        try {
            cursor = database.query(PodItDatabase.PERSON_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Person.Builder personBuilder = new Person.Builder();
                    personBuilder.firstName(cursor.getString(cursor.getColumnIndex(PodItDatabase.FIRST_NAME)));
                    personBuilder.lastName(cursor.getString(cursor.getColumnIndex(PodItDatabase.LAST_NAME)));
                    peopleList.add(personBuilder.build());
                    cursor.moveToNext();
                }
            }
        }  finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        database.close();

        return peopleList;
    }
}