package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Person;

public class EditPerson extends Activity {
    protected CarPodsDatabase carPodsDatabase;

    public EditPerson() {
        super();
    }

    public EditPerson(CarPodsDatabase carPodsDatabase) {
        this.carPodsDatabase = carPodsDatabase;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_person_view);

        getDatabaseConnection();
    }

    protected void getDatabaseConnection() {
        if (carPodsDatabase == null) {
            carPodsDatabase = new CarPodsDatabase(this);
        }
    }

    public void savePerson(View v) {
        Person person = getDataFromView();
        carPodsDatabase.savePerson(person);
    }

    protected Person getDataFromView() {

        return new Person.Builder().firstName(getFirstNameFromView())
                                   .lastName(getLastNameFromView())
                                   .homeLocation(getHomeLocationFromView())
                                   .aboutMe(getAboutMeFromView())
                                   .build();
    }

    protected String getLastNameFromView() {
        return ((EditText)findViewById(R.id.last_name_input)).getText().toString();
    }

    protected String getFirstNameFromView() {
        return ((EditText)findViewById(R.id.first_name_input)).getText().toString();
    }

    protected String getHomeLocationFromView() {
        return ((EditText)findViewById(R.id.home_location_input)).getText().toString();
    }

    protected String getAboutMeFromView() {
        return ((EditText)findViewById(R.id.about_me_input)).getText().toString();
    }


}