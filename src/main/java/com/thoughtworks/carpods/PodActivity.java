package com.thoughtworks.carpods;

import android.app.Activity;
import android.os.Bundle;

public abstract class PodActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PodApplication) getApplication()).inject(this);
    }
}