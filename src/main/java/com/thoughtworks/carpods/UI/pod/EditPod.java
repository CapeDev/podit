package com.thoughtworks.carpods.UI.pod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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

    public void savePod(View v) {
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
        TimePicker returnTimePicker = (TimePicker) findViewById(R.id.return_time_picker);

        int returnTime = Integer.parseInt(returnTimePicker.getCurrentHour() + "" + returnTimePicker.getCurrentMinute());
        Log.v(CLAZZ_TAG, "Read the raw return time as: \"" + returnTime + "\"");
        return returnTime;
    }

    private int getDepartureTimeFromView() {
        TimePicker departureTime = (TimePicker) findViewById(R.id.departure_time_picker);

        int returnVal = Integer.parseInt(departureTime.getCurrentHour() + "" + departureTime.getCurrentMinute());

        Log.v(CLAZZ_TAG, "Read the raw departure time as: \"" + returnVal + "\"");
        return returnVal;
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
                String message = "I've returned with personID: " + personId;
                Log.v(CLAZZ_TAG, "..........................> returned with personId: " + personId);
                showToast(message);

                PeopleDataAccess personDataAccess = new PeopleDataAccess(this);
                // FIXME - I bet there's a better way to handle the +1 for the personId
                Person newPodMember = personDataAccess.getPersonFromDatabaseWithId(personId);
                showToast(newPodMember.getFirstName());
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}