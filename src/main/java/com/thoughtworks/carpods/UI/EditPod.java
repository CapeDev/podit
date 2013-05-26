package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Pod;

import javax.swing.text.View;


public class EditPod extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_pod);
    }

    public void savePodClick(View v) {
        Toast.makeText(getApplicationContext(), "Make me do something!", Toast.LENGTH_SHORT).show();

        Pod pod = getDataFromView();
    }

    private Pod getDataFromView() {
        Pod.Builder podBuilder = new Pod.Builder();
        podBuilder.name(getPodNameFromView());
        podBuilder.homeLocation(getHomeLocationFromView());
        // FIXME - should these be some kind of date object instead of a integer?
        podBuilder.departureTime(getDepartureTimeFromView());
        podBuilder.returnTIme(getReturnTimeFromView());
        podBuilder.about(getAboutFromView());

        // FIXME - how am I going to get the members from the view?

        return podBuilder.build();
    }

    private String getAboutFromView() {
        return ((EditText)findViewById(R.id.about_pod)).getText().toString();
    }

    private int getReturnTimeFromView() {
        String rawReturnTime = ((EditText)findViewById(R.id.return_time)).getText().toString();
        return Integer.parseInt(rawReturnTime);
    }

    private int getDepartureTimeFromView() {
        String rawDepartureTime = ((EditText)findViewById(R.id.departure_time)).getText().toString();
        return Integer.parseInt(rawDepartureTime);
    }

    private String getHomeLocationFromView() {
        return ((EditText)findViewById(R.id.home_location)).getText().toString();
    }

    private String getPodNameFromView() {
        return ((EditText)findViewById(R.id.pod_name)).getText().toString();
    }

    public void cancelClick(View v) {
        finish();
    }
}