package com.thoughtworks.carpods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.thoughtworks.carpods.UI.EditPerson;
import com.thoughtworks.carpods.example.HelloAndroidActivity;

public class ActivitySwitcher extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher_view);
    }

    public void startExample(View view) {
        startActivity(new Intent(this, HelloAndroidActivity.class));
    }

    public void startEditPerson(View view) {
        startActivity(new Intent(this, EditPerson.class));
    }
}