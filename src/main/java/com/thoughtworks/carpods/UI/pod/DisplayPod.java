package com.thoughtworks.carpods.UI.pod;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;
import com.thoughtworks.carpods.plumb.PodActivity;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static com.thoughtworks.carpods.fun.ViewCast.imageView;
import static com.thoughtworks.carpods.fun.ViewCast.textView;


public class DisplayPod extends PodActivity {

    @Inject
    DataAccessFactory dataAccessFor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_pod);

        PodDataAccess carPodsDatabase = dataAccessFor.pods(this);
        Pod pod = carPodsDatabase.getFirstPodInDatabase();
        populateActionBar(pod);
        setPicture("");
        setPodHomeLocation(pod.getHomeLocation());
        setDepartureTime(pod.getDepartureTime());
        setReturnTime(pod.getReturnTime());
        setAbout(pod.getAboutPod());
        setMembers(pod.getMembers());
    }

    private void setPicture(String picture) {
        File imageFile = new File(getFilesDir(), picture);
        if (imageFile.exists() && imageFile.isFile()) {
            imageView(this, R.id.profile_picture).setImageURI(Uri.fromFile(imageFile));
        }
    }

    private void setMembers(List<Person> members) {
        LinearLayout podMemberLayout = (LinearLayout)findViewById(R.id.member_layout);
        for (Person memberToAdd : members) {
            TextView memberNameView = new TextView(this);
            // FIXME - there's a better way to do this with a resources file for text size
            // FIXME - for more information, see: http://stackoverflow.com/questions/9494037/how-to-set-text-size-of-textview-dynamically-for-diffrent-screens
            memberNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            memberNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            memberNameView.setPadding(5, 0, 0, 0);
            memberNameView.setText(memberToAdd.getFirstName() + " " + memberToAdd.getLastName());
            podMemberLayout.addView(memberNameView);
        }
    }

    private void setAbout(String aboutPod) {
        textView(this, R.id.about_pod_field).setText(aboutPod);
    }

    private void setReturnTime(int returnTime) {
        textView(this, R.id.return_time).setText(String.valueOf(returnTime));
    }

    private void setDepartureTime(int departureTime) {
        textView(this, R.id.departure_time).setText(String.valueOf(departureTime));
    }

    private void setPodHomeLocation(String homeLocation) {
        textView(this, R.id.home_location_field).setText(homeLocation);
    }

    private void populateActionBar(Pod pod) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(pod.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}