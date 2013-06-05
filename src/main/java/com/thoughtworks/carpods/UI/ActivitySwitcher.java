package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.people.DisplayPerson;
import com.thoughtworks.carpods.UI.people.EditPerson;
import com.thoughtworks.carpods.UI.people.PeopleList;
import com.thoughtworks.carpods.UI.pod.DisplayPod;
import com.thoughtworks.carpods.UI.pod.EditPod;
import com.thoughtworks.carpods.UI.pod.PodList;
import com.thoughtworks.carpods.data.development.PeoplePopulator;
import com.thoughtworks.carpods.data.development.PodPopulator;
import com.thoughtworks.carpods.data.development.Populator;
import com.thoughtworks.carpods.example.HelloAndroidActivity;
import com.thoughtworks.carpods.example.HelloScrollView;

public class ActivitySwitcher extends Activity {

    private Context applicationContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher);
        applicationContext = getApplicationContext();
    }

    public void startExample(View view) {
        startActivity(new Intent(this, HelloAndroidActivity.class));
    }

    public void startScrollViewExample(View view) {
        startActivity(new Intent(this, HelloScrollView.class));
    }

    public void startEditPerson(View view) {
        startActivity(new Intent(this, EditPerson.class));
    }

    public void startDisplayPerson(View view) {
        startActivity(new Intent(this, DisplayPerson.class));
    }

    public void populatePeople(View view) {
        populate(new PeoplePopulator(applicationContext));
    }

    public void populatePods(View view) {
        populate(new PodPopulator(applicationContext));
    }

    private void populate(Populator populator) {
        populator.populate();
        Toast.makeText(getApplicationContext(), "Done populating", Toast.LENGTH_SHORT).show();
    }

    public void startPeopleList(View view) {
        startActivity(new Intent(this, PeopleList.class));
    }

    public void startCreatePod(View view) {
        startActivity(new Intent(this, EditPod.class));
    }

    public void startDisplayPod(View view) {
        startActivity(new Intent(this, DisplayPod.class));
    }

    public void startPodList(View view) {
        startActivity(new Intent(this, PodList.class));
    }

}