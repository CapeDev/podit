package com.thoughtworks.carpods.UI.pod;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_with_icons);

        if (database == null) {
            database = new PodDataAccess(this);
        }

        List<Pod> podNames = database.getAllPodNames();

        Toast.makeText(this, "I've got " + podNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PoditListAdapter(this, podNames));
        setUpActionBar();
    }

    @Override
    protected Class<? extends Object> getDisplayClass() {
        return DisplayPod.class;
    }

    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
}