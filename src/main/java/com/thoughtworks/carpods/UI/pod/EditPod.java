package com.thoughtworks.carpods.UI.pod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.people.PeopleList;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;


public class EditPod extends Activity {

    private static final int PICK_CONTACT_REQUEST = 1;

    private PodDataAccess podDataAccess;
    private String CLAZZ_TAG = "EditPod";
    private Pod.Builder podBuilder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_pod);

        getDatabaseConnection();

        Log.v(CLAZZ_TAG, "Done with onCreate in EditPod");
    }

    private void getDatabaseConnection() {
        if (podDataAccess == null) {
            podDataAccess = new PodDataAccess(this);
        }
    }

    public void save(MenuItem item) {
        showToast("I'm saving a pod from the menu!");
        Pod pod = getDataFromView();
        podDataAccess.savePod(pod);
        finish();
    }

    private Pod getDataFromView() {
        Pod.Builder podBuilder = new Pod.Builder();
        podBuilder.name(getPodNameFromView());
        podBuilder.homeLocation(getHomeLocationFromView());
        // FIXME - should these be some kind of date object instead of a integer?
        podBuilder.departureTime(getDepartureTimeFromView());
        podBuilder.returnTime(getReturnTimeFromView());
        podBuilder.about(getAboutFromView());

        // FIXME - how am I going to get the members from the view?

        return podBuilder.build();
    }

    private String getAboutFromView() {
        return ((EditText)findViewById(R.id.about_pod)).getText().toString();
    }

    private int getReturnTimeFromView() {
        String rawReturnTime = ((TextView)findViewById(R.id.return_time)).getText().toString();
        rawReturnTime = rawReturnTime.replace(":", "");
        Log.v(CLAZZ_TAG, "Read the raw return time as: \"" + rawReturnTime + "\"");
        return Integer.parseInt(rawReturnTime);
    }

    private int getDepartureTimeFromView() {
        String rawDepartureTime = ((TextView)findViewById(R.id.departure_time)).getText().toString();
        rawDepartureTime = rawDepartureTime.replace(":", "");
        Log.v(CLAZZ_TAG, "Read the raw departure time as: \"" + rawDepartureTime + "\"");
        return Integer.parseInt(rawDepartureTime);
    }

    private String getHomeLocationFromView() {
        return ((Spinner)findViewById(R.id.home_location_spinner)).getSelectedItem().toString();
    }

    private String getPodNameFromView() {
        return ((EditText)findViewById(R.id.pod_name)).getText().toString();
    }

    public void cancelClick(View v) {
        finish();
    }

    public void addMemberClick(View view) {
        Intent peopleListIntent = new Intent(this, PeopleList.class);
        startActivityForResult(peopleListIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CONTACT_REQUEST) {
                long personId = data.getLongExtra("personId", -1);

                PeopleDataAccess personDataAccess = new PeopleDataAccess(this);
                Person newPodMember = personDataAccess.getPersonFromDatabaseWithId(personId);

                // FIXME - what if the activity is editing a pod instead of creating a new one?
                podBuilder = new Pod.Builder();
                podBuilder.member(newPodMember);
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}