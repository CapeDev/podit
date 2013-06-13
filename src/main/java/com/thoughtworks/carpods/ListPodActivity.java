package com.thoughtworks.carpods;

import android.app.ListActivity;
import android.os.Bundle;

public abstract class ListPodActivity extends ListActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PodApplication) getApplication()).inject(this);
    }
}