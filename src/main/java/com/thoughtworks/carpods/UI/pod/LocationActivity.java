package com.thoughtworks.carpods.UI.pod;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.thoughtworks.carpods.R;

public class LocationActivity extends FragmentActivity {

    private GoogleMap map;
    private UiSettings uiSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pod_location);
//        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
//            map.setOnMapClickListener(this);
            map.setMyLocationEnabled(true);

            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        uiSettings = map.getUiSettings();
        map.setMyLocationEnabled(true);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}
