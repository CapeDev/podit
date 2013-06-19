package com.thoughtworks.carpods.plumb;

import android.app.ListActivity;
import android.os.Bundle;
import com.thoughtworks.carpods.PodApplication;

public abstract class ListPodActivity extends ListActivity   {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PodApplication) getApplication()).inject(this);
    }
}