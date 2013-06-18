package com.thoughtworks.carpods.plumb;

import android.app.Activity;
import android.os.Bundle;
import com.thoughtworks.carpods.PodApplication;

public abstract class PodActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PodApplication) getApplication()).inject(this);
    }
}