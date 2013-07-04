package com.thoughtworks.carpods.fun;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class PathUtils {
    public static String getPath(Uri uri, ContentResolver contentResolver) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
