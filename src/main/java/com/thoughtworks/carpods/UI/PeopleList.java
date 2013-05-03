package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Person;

import java.util.List;

public class PeopleList extends ListActivity {

    CarPodsDatabase database;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.people_list_view);

        if (database == null) {
            database = new CarPodsDatabase(this);
        }

        List<Person> peopleNames = database.getAllPeopleNames();

        Toast.makeText(this, "I've got " + peopleNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PeopleAdapter(this, peopleNames));
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Toast.makeText(this, "hey hey kids! I clicked position " + position, Toast.LENGTH_LONG).show();
    }
}