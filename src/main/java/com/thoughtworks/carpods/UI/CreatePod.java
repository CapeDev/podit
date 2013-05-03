package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.thoughtworks.carpods.R;

import javax.swing.text.View;


public class CreatePod extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_pod);
    }

    public void savePodClick(View v) {
        Toast.makeText(getApplicationContext(), "Make me do something!", Toast.LENGTH_SHORT).show();
    }

    public void cancelClick(View v) {
        finish();
    }
}