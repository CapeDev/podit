package com.thoughtworks.carpods.UI.people;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.plumb.ListPodActivity;

import javax.inject.Inject;
import java.util.List;

public class PeopleList extends ListPodActivity {
    static final private String CLASS_TAG = "PeopleList";

    @Inject DataAccessFactory dataAccessFor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.people_list);

        PeopleDataAccess dataAccess = dataAccessFor.people(this);
        List<Person> peopleNames = dataAccess.getAllPeopleNames();

        Toast.makeText(this, "I've got " + peopleNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PeopleAdapter(this, peopleNames));
        setUpActionBar();
    }

//    @Override
//    protected void onListItemClick(ListView listView, View view, int position, long id) {
//        Person item = (Person) listView.getAdapter().getItem(position);
//        Intent intent = new Intent(this, DisplayPerson.class);
//        intent.putExtra("id", (item.getId() + 1));
//        startActivity(intent);
//    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void logMessage (String methodName) {
        Log.v(CLASS_TAG, "-------------------> I'm calling: " + methodName);
    }
}