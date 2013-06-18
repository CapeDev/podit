package com.thoughtworks.carpods.UI.people;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

public class DisplayPerson extends Activity {

    private PeopleDataAccess dataAccess;
    private ViewCast viewCast;

    public DisplayPerson() { }

    protected DisplayPerson(PeopleDataAccess dataAccess, Intent intent) {
        this.dataAccess = dataAccess;
        this.setIntent(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewCast = new ViewCast(this);

        setContentView(R.layout.display_person);

        Person person = person();

        try {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e)
        {
            //this is for test purposes, as ActionBar cannot be set, or mocked.
        }

        setPersonName();
        setHomeLocation(person.getHomeLocation());
        setAboutMe(person.getAboutMe());
    }

    private Person person() {
        if (getIntent().hasExtra("id")) {
            return dataAccess().getPersonFromDatabaseWithId(getIntent().getIntExtra("id", 0));
        }

        return dataAccess().getFirstPersonFromDatabase();
    }

    private PeopleDataAccess dataAccess() {
        if (dataAccess == null) {
            dataAccess = new PeopleDataAccess(this);
        }
        return dataAccess;
    }

    protected void setPersonName() {
        TextView firstNameField = viewCast.textView(R.id.full_name_field);

        firstNameField.setText(String.format("%s %s", person().getFirstName(), person().getLastName()));
        firstNameField.setTextSize(2*viewCast.textView(R.id.full_name_label).getTextSize());
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