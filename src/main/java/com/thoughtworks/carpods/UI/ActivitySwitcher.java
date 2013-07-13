package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.people.DisplayPerson;
import com.thoughtworks.carpods.UI.people.EditPerson;
import com.thoughtworks.carpods.UI.people.PeopleList;
import com.thoughtworks.carpods.UI.pod.DisplayPod;
import com.thoughtworks.carpods.UI.pod.EditPod;
import com.thoughtworks.carpods.UI.pod.PodList;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.data.development.PeoplePopulator;
import com.thoughtworks.carpods.data.development.PodPopulator;
import com.thoughtworks.carpods.data.development.Populator;
import net.hockeyapp.android.UpdateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.carpods.R.string.hockey_app_id;

public class ActivitySwitcher extends Activity {

    private Context applicationContext;

    private SharedPreferences preferences;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.activity_switcher);
        applicationContext = getApplicationContext();
        UpdateManager.register(this, getString(hockey_app_id));

        if (!preferences.getBoolean("initialized", false)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("initialized", true);
            populate(new PeoplePopulator(applicationContext));
            populate(new PodPopulator(applicationContext));
            editor.commit();
        }
    }

    public void startEditPerson(View view) {
        startActivity(new Intent(this, EditPerson.class));
    }

    public void startDisplayPerson(View view) {
        startActivity(new Intent(this, DisplayPerson.class));
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