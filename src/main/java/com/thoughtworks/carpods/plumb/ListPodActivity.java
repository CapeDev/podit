package com.thoughtworks.carpods.plumb;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.thoughtworks.carpods.PodApplication;
import com.thoughtworks.carpods.UI.people.DisplayPerson;
import com.thoughtworks.carpods.UI.pod.EditPod;
import com.thoughtworks.carpods.data.Listable;
import com.thoughtworks.carpods.data.Person;

public abstract class ListPodActivity extends ListActivity  {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PodApplication) getApplication()).inject(this);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Listable item = (Listable) getListAdapter().getItem(position);
        Intent intent = new Intent(this, getDisplayClass());
        intent.putExtra("id", item.getId());
        startActivity(intent);
    }



    protected void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract Class<? extends Object> getDisplayClass();

}