package com.thoughtworks.carpods.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PodItDatabase extends SQLiteOpenHelper {
    private static final String CLASS_TAG = "PodItDatabase";
    private static final String DATABASE_NAME = "PodItDatabase";
    private static final int DATABASE_VERSION = 1;

    public static final String ROWID = "_ID";

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

    PodItDatabase(Context context) {
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