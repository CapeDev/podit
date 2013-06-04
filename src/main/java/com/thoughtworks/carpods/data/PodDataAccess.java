package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PodDataAccess {

    private static final String CLASS_TAG = "PodDataAccess";

    private SQLiteDatabase database;
    private final PodItDatabase podItDatabase;

    public PodDataAccess(Context context) {
        podItDatabase = PodItDatabase.getInstance(context);
    }

    public List<Pod> getAllPodNames() {
        Cursor cursor = null;

        String[] columns = {PodItDatabase.ROWID, PodItDatabase.POD_NAME};
        String selection = null;
        String[] selectionArgs = null;

        List<Pod> podList = new ArrayList<Pod>();

        database = podItDatabase.getWritableDatabase();
        try {
            cursor = database.query(PodItDatabase.POD_TABLE, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Pod.Builder podBuilder = new Pod.Builder();
                    podBuilder.name(cursor.getString(cursor.getColumnIndex(PodItDatabase.POD_NAME)));
                    podBuilder.id(cursor.getInt(cursor.getColumnIndex(PodItDatabase.ROWID)));
                    podList.add(podBuilder.build());
                    cursor.moveToNext();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        database.close();

        return podList;
    }

    public long savePod(Pod pod) {
        String table = PodItDatabase.POD_TABLE;

        ContentValues podInfo = new ContentValues();
        podInfo.put(PodItDatabase.POD_NAME, pod.getName());
        podInfo.put(PodItDatabase.POD_HOME_LOCATION, pod.getHomeLocation());
        podInfo.put(PodItDatabase.POD_DEPARTURE_TIME, pod.getDepartureTime());
        podInfo.put(PodItDatabase.POD_RETURN_TIME, pod.getReturnTime());
        podInfo.put(PodItDatabase.ABOUT_POD, pod.getAboutPod());

        database = podItDatabase.getWritableDatabase();
        long podId = database.insert(table, null, podInfo);
        database.close();

        return podId;
    }

    public Pod getFirstPodInDatabase() {
        String[] columns = {PodItDatabase.ROWID, PodItDatabase.POD_NAME, PodItDatabase.POD_HOME_LOCATION, PodItDatabase.POD_DEPARTURE_TIME, PodItDatabase.POD_RETURN_TIME, PodItDatabase.ABOUT_POD};
        String selection = PodItDatabase.ROWID + " = ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = null;
        Pod.Builder podBuilder = new Pod.Builder();
        database = podItDatabase.getWritableDatabase();
        try {
            cursor = database.query(PodItDatabase.POD_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                Log.v(CLASS_TAG, "retrieved " + cursor.getCount() + " Pods from the database");
                cursor.moveToLast();
                podBuilder.name(cursor.getString(cursor.getColumnIndex(PodItDatabase.POD_NAME)));
                podBuilder.homeLocation(cursor.getString(cursor.getColumnIndex(PodItDatabase.POD_HOME_LOCATION)));
                podBuilder.departureTime(cursor.getInt(cursor.getColumnIndex(PodItDatabase.POD_DEPARTURE_TIME)));
                podBuilder.returnTime(cursor.getInt(cursor.getColumnIndex(PodItDatabase.POD_RETURN_TIME)));
                podBuilder.about(cursor.getString(cursor.getColumnIndex(PodItDatabase.ABOUT_POD)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return podBuilder.build();
    }
}