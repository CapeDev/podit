package com.thoughtworks.carpods.UI.pod;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
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
        ((TextView)findViewById(R.id.return_time)).setText(String.valueOf(returnTime));
    }

    private void setDepartureTime(int departureTime) {
        ((TextView)findViewById(R.id.departure_time)).setText(String.valueOf(departureTime));
    }

    private void setPodHomeLocation(String homeLocation) {
        ((TextView)findViewById(R.id.home_location)).setText(homeLocation);
    }

    private void setPodName(String name) {
        ((TextView)findViewById(R.id.pod_name)).setText(name);
    }
}