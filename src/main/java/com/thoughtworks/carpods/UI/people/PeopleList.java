package com.thoughtworks.carpods.UI.people;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;

import java.util.List;

public class PeopleList extends ListActivity {

    PeopleDataAccess database;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.people_list);

        if (database == null) {
            database = new PeopleDataAccess(this);
        }

        List<Person> peopleNames = database.getAllPeopleNames();

        Toast.makeText(this, "I've got " + peopleNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PeopleAdapter(this, peopleNames));
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Person item = (Person) listView.getAdapter().getItem(position);
        Intent intent = new Intent(this, DisplayPerson.class);
        intent.putExtra("id", item.getId());
        startActivity(intent);
    }
}