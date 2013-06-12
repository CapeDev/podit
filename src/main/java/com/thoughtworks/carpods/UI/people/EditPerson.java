package com.thoughtworks.carpods.UI.people;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

public class EditPerson extends Activity {
    private PeopleDataAccess peopleDataAccess;
    private ViewCast viewCast;

    public EditPerson() {
        super();
    }

    public EditPerson(PeopleDataAccess peopleDataAccess) {
        this.peopleDataAccess = peopleDataAccess;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewCast = new ViewCast(this);

        setContentView(R.layout.edit_person);

        getDatabaseConnection();
    }

    protected void getDatabaseConnection() {
        if (peopleDataAccess == null) {
            peopleDataAccess = new PeopleDataAccess(this);
        }
    }

    public void save(MenuItem item) {
        Person person = getDataFromView();
        peopleDataAccess.savePerson(person);
        finish();
    }

    protected Person getDataFromView() {
        return new Person.Builder().firstName(getFirstNameFromView())
                                   .lastName(getLastNameFromView())
                                   .homeLocation(getHomeLocationFromView())
                                   .aboutMe(getAboutMeFromView())
                                   .build();
    }

    protected String getLastNameFromView() {
        return viewCast.editText(R.id.last_name_input).getText().toString();
    }

    protected String getFirstNameFromView() {
        return viewCast.editText(R.id.first_name_input).getText().toString();
    }

    protected String getHomeLocationFromView() {
        return viewCast.editText(R.id.home_location_input).getText().toString();
    }

    protected String getAboutMeFromView() {
        return viewCast.editText(R.id.about_me_input).getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_person, menu);
        return true;
    }
}