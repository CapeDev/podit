package com.thoughtworks.carpods.fun;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewCast {

    public static EditText editText(Activity activity, int id) {
        return viewOf(id, EditText.class, activity);
    }

    public static TextView textView(Activity activity, int id) {
        return viewOf(id, TextView.class, activity);
    }

    public static ImageButton imageButton(Activity activity, int id) {
        return viewOf(id, ImageButton.class, activity);
    }

    public static ImageView imageView(Activity activity, int id) {
        return viewOf(id, ImageView.class, activity);
    }

    private static <T> T viewOf(int id, Class<T> type, Activity activity) {
        return type.cast(activity.findViewById(id));
    }
}
