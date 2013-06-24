package com.thoughtworks.carpods.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PodMemberDataAccess {

    private SQLiteDatabase database;
    private SQLiteOpenHelper podItDatabase;

    private static final String CLASS_TAG = "PodMemberDataAccess";

    public PodMemberDataAccess(SQLiteOpenHelper database) {
        this.podItDatabase = database;
    }

    public void addMembersToPod(List<Integer> members, long podId) {

        database = podItDatabase.getWritableDatabase();
        for (Integer memberId : members) {
            ContentValues info  = new ContentValues();
            info.put(PodItDatabase.POD_ID, podId);
            info.put(PodItDatabase.MEMBER_ID, memberId);
            database.insert(PodItDatabase.POD_MEMBER_TABLE, null, info);
            Log.d(CLASS_TAG, "saved memberId: " + memberId + " with podId: " + podId + " to the local database");
        }
        database.close();
    }

    public List<Integer> getMemberIdsForPod(long podId) {
        String[] columns = {PodItDatabase.MEMBER_ID};
        String selection = PodItDatabase.POD_ID + " = ?";
        String[] selectionArgs = {String.valueOf(podId)};

        database = podItDatabase.getWritableDatabase();
        Cursor cursor = null;
        List<Integer> memberIds = new ArrayList<Integer>();
        try {
            cursor = database.query(PodItDatabase.POD_MEMBER_TABLE, columns, selection, selectionArgs, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    memberIds.add(cursor.getInt(cursor.getColumnIndex(PodItDatabase.MEMBER_ID)));
                    cursor.moveToNext();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return memberIds;
    }
}