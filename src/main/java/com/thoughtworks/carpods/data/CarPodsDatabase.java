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
        DBHelper dbHelper = new DBHelper(this.context);
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
        this.open();
        database.insert(table, null, info);
        this.close();
    }

    public String getLastPerson() {
        String personsName = "";
        Cursor cursor = null;

        String[] columns = {ROWID, NAME};
        String whereClause = ROWID + " > ?";
        String[] whereArgs = {"0"};

        this.open();
        try {
            cursor = this.database.query(PERSON_TABLE, columns, whereClause, whereArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                personsName = cursor.getString(cursor.getColumnIndex(NAME));
            }

        }  finally {
            if (cursor != null) {
                cursor.close();
            }
            this.close();
        }

        return personsName;
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
