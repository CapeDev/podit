package com.thoughtworks.carpods.fun;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.FileOutputStream;
import java.util.Calendar;

public class FileMoveUtils {
    private static final String TAG = "FileMoveUtils";

    public static String tryToSaveBitmapInInternalStorage(Context context, Bitmap bitmap, String prefix) {
        String fileName = fileNameFor(prefix);
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            return fileName;
        } catch (Exception e) {
            Log.d(TAG, "Unable to save selected picture");
            return "";
        }
    }

    private static String fileNameFor(String prefix) {
        return String.format("%s_%d.jpg", prefix, Calendar.getInstance().getTimeInMillis()).toLowerCase();
    }
}
