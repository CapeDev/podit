package com.thoughtworks.carpods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.thoughtworks.carpods.UI.DisplayPerson;
import com.thoughtworks.carpods.UI.EditPerson;
import com.thoughtworks.carpods.UI.PeopleListLoader;
import com.thoughtworks.carpods.data.PeoplePopulator;
import com.thoughtworks.carpods.example.HelloAndroidActivity;

public class ActivitySwitcher extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher_view);
    }

    public void startExample(View view) {
        startActivity(new Intent(this, HelloAndroidActivity.class));
    }

    public void startEditPerson(View view) {
        startActivity(new Intent(this, EditPerson.class));
    }

    public void startDisplayPerson(View view) {
        startActivity(new Intent(this, DisplayPerson.class));
    }

    public void populatePeople(View view) {
        PeoplePopulator populator = new PeoplePopulator(getApplicationContext());
        populator.populate();

        Toast toast = Toast.makeText(getApplicationContext(), "Done populating People", Toast.LENGTH_LONG);
        toast.show();
    }

    public void startPeopleList(View view) {
        startActivity(new Intent(this, PeopleListLoader.class));
        // Toast toast = Toast.makeText(getApplicationContext(), "The People ListView Activity Has Not Yet Been Implemented", Toast.LENGTH_LONG);
        // toast.show();
    }
}