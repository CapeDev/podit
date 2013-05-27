package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CarPodsDatabase {

    private static final String CLASS_TAG = "CarPodsDatabase";

    private static final String DATABASE_NAME = "carpodsDatabase";

    public static final String ROWID = "_id";

    private static final String EXAMPLE_TABLE = "exampleTable";
    public static final String PERSON_TABLE = "personTable";

    private static final String EXAMPLE_STRING = "example_string";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    private static final String HOME_LOCATION = "home_location";
    private static final String ABOUT_ME = "about_name";


    private static final String POD_TABLE = "podTable";
    private static final String POD_NAME = "pod_name";
    private static final String POD_HOME_LOCATION = "pod_home";
    private static final String POD_DEPARTURE_TIME = "pod_departure_time";
    private static final String POD_RETURN_TIME = "pod_return_time";
    private static final String ABOUT_POD = "about_pod";

    private static final String POD_MEMBER_TABLE = "podMemberTable";
    private static final String POD_ID = "podId";
    private static final String MEMBER_ID = "memberId";

    private final Context context;

    private SQLiteDatabase database;

    private static final String EXAMPLE_TABLE_CREATE_STMT =
            "create table " + EXAMPLE_TABLE
                    + " ("
                    + ROWID + " integer primary key autoincrement, "
                    + EXAMPLE_STRING + " text not null"
                    + ");";

    private static final String PERSON_TABLE_CREATE_STMT =
            "create table " + PERSON_TABLE
                    + " ("
                    + ROWID + " integer primary key autoincrement, "
                    + FIRST_NAME + " text not null, "
                    + LAST_NAME + " text not null, "
                    + ABOUT_ME + " text, "
                    + HOME_LOCATION + " text not null"
                    + ");";

    private static final String POD_TABLE_CREATE_STMT =
            "create table " + POD_TABLE
                    + " ("
                    + ROWID + " integer primary key autoincrement, "
                    + POD_NAME + " text not null, "
                    + POD_HOME_LOCATION + " text not null, "
                    + POD_DEPARTURE_TIME + " text not null, "
                    + POD_RETURN_TIME + " text not null, "
                    + ABOUT_POD + " text "
                    + ");";

    private static final String POD_MEMBER_CREATE_STMT =
            "create table " + POD_MEMBER_TABLE
                + " ("
                + POD_ID + " integer not null, "
                + MEMBER_ID + " integer not null, "
                + "foreign key(" + POD_ID + ") references " + POD_TABLE + "(" + ROWID + "), "
                + "foreign key(" + MEMBER_ID + ") references " + PERSON_TABLE + "(" + ROWID + "), "
                + "primary key (" + POD_ID + ", " + MEMBER_ID + ") "
                + ");";



    public CarPodsDatabase (Context context) {
        this.context = context;
    }

    public CarPodsDatabase open() throws SQLException {
        DBHelper dbHelper = new DBHelper(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    public void saveStringExample(String personsName) {
        String table = EXAMPLE_TABLE;

        ContentValues info = new ContentValues();
        info.put(EXAMPLE_STRING, personsName);
        this.open();
        database.insert(table, null, info);
        this.close();
    }

    public String getExampleString() {
        String exampleString = "";
        Cursor cursor = null;

        String[] columns = {ROWID, EXAMPLE_STRING};
        String whereClause = ROWID + " > ?";
        String[] whereArgs = {"0"};

        this.open();
        try {
            cursor = this.database.query(EXAMPLE_TABLE, columns, whereClause, whereArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                exampleString = cursor.getString(cursor.getColumnIndex(EXAMPLE_STRING));
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
            this.close();
        }

        return exampleString;
    }

    public void savePerson(Person person) {
        String table = PERSON_TABLE;

        ContentValues info = new ContentValues();
        info.put(FIRST_NAME, person.getFirstName());
        info.put(LAST_NAME, person.getLastName());
        info.put(HOME_LOCATION, person.getHomeLocation());
        info.put(ABOUT_ME, person.getAboutMe());

        this.open();
        database.insert(table, null, info);
        this.close();

        Log.d("CarPodsDatabase", "saved " + person.getFirstName() + " to the local database");
    }

    public Boolean isOpen() {
        Boolean isOpen = false;
        if (database != null) {
            isOpen = database.isOpen();
        }
        return isOpen;
    }

    public Person getFirstPersonFromDatabase() {
        Cursor cursor = null;

        String[] columns = {ROWID, FIRST_NAME, LAST_NAME, HOME_LOCATION, ABOUT_ME};
        String selection = ROWID + " = ?";
        String[] selectionArgs = {"1"};

        Person.Builder personBuilder = new Person.Builder();

        this.open();
        try {
            cursor = database.query(PERSON_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                personBuilder.firstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
                personBuilder.lastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
                personBuilder.homeLocation(cursor.getString(cursor.getColumnIndex(HOME_LOCATION)));
                personBuilder.aboutMe(cursor.getString(cursor.getColumnIndex(ABOUT_ME)));
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
            this.close();
        }

        return personBuilder.build();
    }

    public Cursor getCursorWithAllPeople() {
        Cursor cursor;

        String table = PERSON_TABLE;
        String[] columns = {ROWID, FIRST_NAME, LAST_NAME, HOME_LOCATION, ABOUT_ME};

        cursor = database.query(table, columns, null, null, null, null, null, null);

        return cursor;
    }

    public List<Person> getAllPeopleNames() {
        this.open();
        Cursor cursor = null;

        String[] columns = {ROWID, FIRST_NAME, LAST_NAME};
        String selection = null;
        String[] selectionArgs = null;

        List<Person> peopleList = new ArrayList<Person>();

        this.open();
        try {
            cursor = database.query(PERSON_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Person.Builder personBuilder = new Person.Builder();
                    personBuilder.firstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
                    personBuilder.lastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
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

    public long savePod(Pod pod) {
        String table = POD_TABLE;

        ContentValues podInfo = new ContentValues();
        podInfo.put(POD_NAME, pod.getName());
        podInfo.put(POD_HOME_LOCATION, pod.getHomeLocation());
        podInfo.put(POD_DEPARTURE_TIME, pod.getDepartureTime());
        podInfo.put(POD_RETURN_TIME, pod.getReturnTime());
        podInfo.put(ABOUT_POD, pod.getAboutPod());

        this.open();
        long podId = database.insert(table, null, podInfo);
        this.close();

        return podId;
    }

    public void savePodMembersToPod(List<Person> peopleToAdd, int podId) {

        this.open();

        for (Person newMember : peopleToAdd) {
            ContentValues memberInfo = new ContentValues();
            memberInfo.put(POD_ID, podId);
            memberInfo.put(MEMBER_ID, newMember.getId());
            database.insert(POD_MEMBER_TABLE, null, memberInfo);
        }

        this.close();
    }

    public Pod getFirstPodInDatabase() {
        String[] columns = {ROWID, POD_NAME, POD_HOME_LOCATION, POD_DEPARTURE_TIME, POD_RETURN_TIME, ABOUT_POD};
        String selection = ROWID + " = ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = null;
        Pod.Builder podBuilder = new Pod.Builder();
        this.open();
        try {
            cursor = database.query(POD_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                Log.v(CLASS_TAG, "retrieved " + cursor.getCount() + " Pods from the database");
                cursor.moveToLast();
                podBuilder.name(cursor.getString(cursor.getColumnIndex(POD_NAME)));
                podBuilder.homeLocation(cursor.getString(cursor.getColumnIndex(POD_HOME_LOCATION)));
                podBuilder.departureTime(cursor.getInt(cursor.getColumnIndex(POD_DEPARTURE_TIME)));
                podBuilder.returnTime(cursor.getInt(cursor.getColumnIndex(POD_RETURN_TIME)));
                podBuilder.about(cursor.getString(cursor.getColumnIndex(ABOUT_POD)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return podBuilder.build();

    }

    private static class DBHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;

        DBHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase db) {
            db.execSQL(EXAMPLE_TABLE_CREATE_STMT);
            db.execSQL(PERSON_TABLE_CREATE_STMT);
            db.execSQL(POD_TABLE_CREATE_STMT);
            db.execSQL(POD_MEMBER_CREATE_STMT);
        }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.v(CLASS_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + " which will destroy all old data.");
            db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + POD_TABLE);
            onCreate(db);
        }

        public synchronized void close() {
            super.close();
        }
    }
}
