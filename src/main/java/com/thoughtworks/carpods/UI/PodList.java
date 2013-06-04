package com.thoughtworks.carpods.UI;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;

import java.util.List;


public class PodList extends ListActivity {

    PodDataAccess database;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pod_list);

        if (database == null) {
            database = new PodDataAccess(this);
        }

        List<Pod> podNames = database.getAllPodNames();

        Toast.makeText(this, "I've got " + podNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PodAdapter(this, podNames));
    }
}