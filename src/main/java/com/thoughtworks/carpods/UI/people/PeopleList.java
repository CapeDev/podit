package com.thoughtworks.carpods.UI.people;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;

import java.util.List;

public class PeopleList extends ListActivity {
    static final private String CLASS_TAG = "PeopleList";

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
        Toast.makeText(this, "hey hey kids! I clicked position " + position, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        // FIXME - what happens when there is a big list?  what id gets returned? i.e. whats the difference between 'position' and 'id'?
        intent.putExtra("personId", id + 1);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        logMessage("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logMessage("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMessage("onDestroy");
    }

    private void logMessage (String methodName) {
        Log.v(CLASS_TAG, "-------------------> I'm calling: " + methodName);
    }
}