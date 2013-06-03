package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CarPodsDatabase {

    private final Context context;

    private SQLiteDatabase database;

    public CarPodsDatabase (Context context) {
        this.context = context;
    }

    public CarPodsDatabase open() throws SQLException {
        PodItDatabase podItDatabase = PodItDatabase.getInstance(this.context);
        database = podItDatabase.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    public void savePerson(Person person) {
        String table = PodItDatabase.PERSON_TABLE;

        ContentValues info = new ContentValues();
        info.put(PodItDatabase.FIRST_NAME, person.getFirstName());
        info.put(PodItDatabase.LAST_NAME, person.getLastName());
        info.put(PodItDatabase.HOME_LOCATION, person.getHomeLocation());
        info.put(PodItDatabase.ABOUT_ME, person.getAboutMe());

        this.open();
        database.insert(table, null, info);
        this.close();

        Log.d("CarPodsDatabase", "saved " + person.getFirstName() + " to the local database");
    }

    public Person getFirstPersonFromDatabase() {
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.FIRST_NAME, PodItDatabase.LAST_NAME, PodItDatabase.HOME_LOCATION, PodItDatabase.ABOUT_ME};
        String selection = PodItDatabase.ROWID + " = ?";
        String[] selectionArgs = {"1"};

        Person.Builder personBuilder = new Person.Builder();

        this.open();
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
            this.close();
        }

        return personBuilder.build();
    }

    public List<Person> getAllPeopleNames() {
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.FIRST_NAME, PodItDatabase.LAST_NAME};
        String selection = null;
        String[] selectionArgs = null;

        List<Person> peopleList = new ArrayList<Person>();

        this.open();
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
        this.close();

        return peopleList;
    }
}