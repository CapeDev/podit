package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;


public class EditPod extends Activity {

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
}