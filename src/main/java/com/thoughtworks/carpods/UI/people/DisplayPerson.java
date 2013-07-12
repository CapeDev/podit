package com.thoughtworks.carpods.UI.people;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.plumb.PodActivity;

import javax.inject.Inject;
import java.io.File;

import static com.thoughtworks.carpods.fun.ViewCast.imageView;
import static com.thoughtworks.carpods.fun.ViewCast.textView;

public class DisplayPerson extends PodActivity {
    @Inject DataAccessFactory dataAccessFor;

    private PeopleDataAccess dataAccess;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataAccess = dataAccessFor.people(this);

        setContentView(R.layout.display_person);

        Person person = person();

        populateActionBar(person);
        setPicture(person.iconPath());
        setPersonName();
        setHomeLocation(person.getHomeLocation());
        setAboutMe(person.getAboutMe());
    }

    private void populateActionBar(Person person) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(String.format("%s %s", person.getFirstName(), person.getLastName()));
        }
    }

    private void setPicture(String picture) {
        File imageFile = new File(getFilesDir(), picture);
        if (imageFile.exists() && imageFile.isFile()) {
            imageView(this, R.id.profile_picture).setImageURI(Uri.fromFile(imageFile));
        }
    }

    private Person person() {
        if (getIntent().hasExtra("personId")) {
            return dataAccess.getPersonFromDatabaseWithId(getIntent().getIntExtra("personId", 0));
        }

        return dataAccess.getFirstPersonFromDatabase();
    }

    protected void setPersonName() {
        TextView firstNameField = textView(this, R.id.full_name_field);

        firstNameField.setText(String.format("%s %s", person().getFirstName(), person().getLastName()));
        firstNameField.setTextSize(2* textView(this, R.id.full_name_label).getTextSize());
    }

    private void setHomeLocation(String homeLocation) {
        textView(this, R.id.home_location).setText(homeLocation);
    }

    private void setAboutMe(String aboutMe) {
        textView(this, R.id.about_me).setText(aboutMe);
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