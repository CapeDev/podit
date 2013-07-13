package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.thoughtworks.carpods.CarPodApplication;
import com.thoughtworks.carpods.PodApplication;
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
import com.thoughtworks.carpods.data.development.PopulatorFactory;
import net.hockeyapp.android.UpdateManager;

import javax.inject.Inject;

import static com.thoughtworks.carpods.R.string.hockey_app_id;

public class ActivitySwitcher extends Activity {

    protected PopulatorFactory populatorFactory = new PopulatorFactory();
    private Context applicationContext;

    @Inject
    PeoplePopulator peoplePopulator;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((PodApplication)getApplication()).inject(this);

        setContentView(R.layout.activity_switcher);
        applicationContext = getApplicationContext();
        UpdateManager.register(this, getString(hockey_app_id));
        populateAll();
    }

    public void startEditPerson(View view) {
        startActivity(new Intent(this, EditPerson.class));
    }

    public void startDisplayPerson(View view) {
        startActivity(new Intent(this, DisplayPerson.class));
    }

    public void populateAll() {
        populate(populatorFactory.createPeoplePopulator(applicationContext));
        populate(populatorFactory.createPodPopulator(applicationContext));
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