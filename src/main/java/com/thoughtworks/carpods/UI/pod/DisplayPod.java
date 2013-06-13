package com.thoughtworks.carpods.UI.pod;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;


public class DisplayPod extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_pod);

        PodDataAccess carPodsDatabase = new PodDataAccess(this);
        Pod pod = carPodsDatabase.getFirstPodInDatabase();
        setPodName(pod.getName());
        setPodHomeLocation(pod.getHomeLocation());
        setDepartureTime(pod.getDepartureTime());
        setReturnTime(pod.getReturnTime());
        setAbout(pod.getAboutPod());
    }

    private void setAbout(String aboutPod) {
        ((TextView)findViewById(R.id.about_pod)).setText(aboutPod);
    }

    private void setReturnTime(int returnTime) {
        ((TimePicker) findViewById(R.id.return_time_picker)).setCurrentHour(hourOf(returnTime));
        ((TimePicker) findViewById(R.id.return_time_picker)).setCurrentMinute(minuteOf(returnTime));
    }

    public Integer minuteOf(int returnTime) {
        String returnString = String.valueOf(returnTime);
        return Integer.parseInt(returnString.substring(returnString.length() - 2));
    }

    public Integer hourOf(int returnTime) {
        String returnString = String.valueOf(returnTime);
        return Integer.parseInt(returnString.substring(0, returnString.length() - 2));
    }

    private void setDepartureTime(int departureTime) {
        ((TimePicker) findViewById(R.id.departure_time_picker)).setCurrentHour(hourOf(departureTime));
        ((TimePicker) findViewById(R.id.departure_time_picker)).setCurrentMinute(minuteOf(departureTime));
    }

    private void setPodHomeLocation(String homeLocation) {
        ((TextView)findViewById(R.id.home_location)).setText(homeLocation);
    }

    private void setPodName(String name) {
        ((TextView)findViewById(R.id.pod_name)).setText(name);
    }
}