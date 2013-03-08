package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CarPodsDatabase {

    private static final String CLASS_TAG = "CarPodsDatabase";

    public static final String DATABASE_NAME = "carpodsDatabase";

    public static final String PERSON_TABLE = "personTable";
    public static final String ROWID = "_id";
    public static final String NAME = "name";

    private final Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    private static final String PERSON_TABLE_CREATE_STMT =
            "create table " + PERSON_TABLE
                    + " ("
                    + ROWID + " integer primary key autoincrement, "
                    + NAME + " text not null"
                    + ");";

    public CarPodsDatabase (Context context) {
        this.context = context;
    }

    public CarPodsDatabase open()
            throws SQLException {
        dbHelper = new DBHelper(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    public void savePerson(String personsName) {
        String table = PERSON_TABLE;

        ContentValues info = new ContentValues();
        info.put(NAME, personsName);
        database.insert(table, null, info);
    }

    public String getFirstPerson() {
        String personsName = "";
        Cursor cursor = null;

        String[] columns = {ROWID, NAME};
        String whereClause = ROWID + " = ?";
        String[] whereArgs = {String.valueOf(0)};

        try {
           cursor = this.database.query(PERSON_TABLE, columns, whereClause, whereArgs, null, null, null, null);

            if (cursor != null) {

                cursor.moveToFirst();

                if (cursor.getCount() > 0) {
                    personsName = cursor.getString(cursor.getColumnIndex(NAME));
                }
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return personsName;
//        Cursor cursor = null;
//        String note = null;
//
//        String[] columns = {DBC_ROWID, DBC_NOTES};
//        String whereClause = DBC_TASKID + " = ?" + " AND " + DBC_STAMPID + " = ?";
//        String[] whereArgs = {String.valueOf(taskID), String.valueOf(stampID) };
//
//        try {
//            if (verboseLogging) {
//                Log.v(CLASS_TAG, "fetchOneNote() - opening a cursor");
//            }
//
//            cursor = this.database.query(true, DBT_NOTES, columns, whereClause, whereArgs, null, null, null, null);
//            if (verboseLogging) {
//                Log.v(CLASS_TAG, "fetchOneNote() - retreived a cursor");
//            }
//
//            if (cursor != null) {
//                cursor.moveToFirst();
//
//                if (cursor.getCount() > 0) {
//                    // task already has a note, get its content and put it on the screen
//                    note = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbAdapter.DBC_NOTES));
//                }
//            }
//        } finally {
//            if (cursor != null) {
//                if (verboseLogging) {
//                    Log.v(CLASS_TAG, "fetchOneNote() - closing a cursor");
//                }
//                cursor.close();
//            }
//        }
//        return note;

    }

    private static class DBHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;

        DBHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase db) {
            db.execSQL(PERSON_TABLE_CREATE_STMT);
        }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.v(CLASS_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + " which will destroy all old data.");
            db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE);
            onCreate(db);
        }

        public synchronized void close() {
            super.close();
        }
    }
}
