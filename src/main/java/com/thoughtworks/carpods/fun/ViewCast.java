package com.thoughtworks.carpods.fun;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewCast {
    private final Activity activity;

    public ViewCast(Activity activity) {
        this.activity = activity;
    }

    public EditText editText(int viewId) {
        return viewOf(viewId, EditText.class);
    }

    public TextView textView(int viewId) {
        return viewOf(viewId, TextView.class);
    }

    public ImageButton imageButton(int viewId) {
        return viewOf(viewId, ImageButton.class);
    }

    private <T> T viewOf(int viewId, Class<T> type) {
        return type.cast(activity.findViewById(viewId));
    }
}
