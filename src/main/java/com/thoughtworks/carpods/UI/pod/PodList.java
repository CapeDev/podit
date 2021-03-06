package com.thoughtworks.carpods.UI.pod;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.PoditListAdapter;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;
import com.thoughtworks.carpods.plumb.ListPodActivity;

import java.util.List;


public class PodList extends ListPodActivity {

    PodDataAccess database;
    List<Pod> podNames;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_with_icons);

        if (database == null) {
            database = new PodDataAccess(this);
        }
        setUpActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        podNames = database.getAllPodNames();
        Toast.makeText(this, "I've got " + podNames.size() + " from the database.", Toast.LENGTH_LONG).show();
        setListAdapter(new PoditListAdapter(this, podNames));
    }

    @Override
    protected Class<? extends Object> getDisplayClass() {
        return DisplayPod.class;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add(MenuItem unused) {
        Toast.makeText(this, "I just clicked the add button!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, EditPod.class));
    }
}