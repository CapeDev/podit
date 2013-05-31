package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Person;

public class DisplayPerson extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_person);
        CarPodsDatabase carPodsDatabase = new CarPodsDatabase(this);
        Person person = carPodsDatabase.getFirstPersonFromDatabase();
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
        setHomeLocation(person.getHomeLocation());
        setAboutMe(person.getAboutMe());
    }

    private void setFirstName(String firstName) {
        ((TextView)findViewById(R.id.first_name)).setText(firstName);
    }

    private void setLastName(String lastName) {
        ((TextView)findViewById(R.id.last_name)).setText(lastName);
    }

    private void setHomeLocation(String homeLocation) {
        ((TextView)findViewById(R.id.home_location)).setText(homeLocation);
    }

    private void setAboutMe(String aboutMe) {
        ((TextView)findViewById(R.id.about_me)).setText(aboutMe);
    }
}