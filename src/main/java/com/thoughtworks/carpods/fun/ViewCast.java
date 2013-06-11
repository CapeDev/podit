package com.thoughtworks.carpods.fun;

import android.app.Activity;
import android.widget.EditText;

public class ViewCast {
    private final Activity activity;

    public ViewCast(Activity activity) {
        this.activity = activity;
    }

    public EditText editText(int viewId) {
        return viewOf(viewId, EditText.class);
    }

    private <T> T viewOf(int viewId, Class<T> type) {
        return type.cast(activity.findViewById(viewId));
    }
}
