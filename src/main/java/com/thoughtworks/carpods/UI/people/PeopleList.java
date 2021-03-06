package com.thoughtworks.carpods.UI.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.PoditListAdapter;
import com.thoughtworks.carpods.UI.pod.EditPod;
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

        setContentView(R.layout.list_with_icons);

        PeopleDataAccess dataAccess = dataAccessFor.people(this);
        List<Person> peopleNames = dataAccess.getAllPeopleNames();

        Toast.makeText(this, "I've got " + peopleNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PoditListAdapter(this, peopleNames));
        setUpActionBar();
    }

    @Override
    protected Class<DisplayPerson> getDisplayClass() {
        return DisplayPerson.class;
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