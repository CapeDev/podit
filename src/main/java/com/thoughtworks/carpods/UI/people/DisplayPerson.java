package com.thoughtworks.carpods.UI.people;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import com.thoughtworks.carpods.PodActivity;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

import javax.inject.Inject;

public class DisplayPerson extends PodActivity {
    @Inject DataAccessFactory dataAccessFor;

    private PeopleDataAccess dataAccess;
    private ViewCast viewCast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewCast = new ViewCast(this);
        dataAccess = dataAccessFor.people(this);

        setContentView(R.layout.display_person);

        Person person = person();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(String.format("%s %s", person.getFirstName(), person.getLastName()));

        setHomeLocation(person.getHomeLocation());
        setAboutMe(person.getAboutMe());
    }

    private Person person() {
        if (getIntent().hasExtra("id")) {
            return dataAccess.getPersonFromDatabaseWithId(getIntent().getIntExtra("id", 0));
        }

        return dataAccess.getFirstPersonFromDatabase();
    }

    private void setHomeLocation(String homeLocation) {
        viewCast.textView(R.id.home_location).setText(homeLocation);
    }

    private void setAboutMe(String aboutMe) {
        viewCast.textView(R.id.about_me).setText(aboutMe);
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