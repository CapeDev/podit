package com.thoughtworks.carpods.UI.pod;

import android.app.ActionBar;
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
import java.util.List;


public class DisplayPod extends PodActivity {

    @Inject
    DataAccessFactory dataAccessFor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_pod);

        PodDataAccess carPodsDatabase = dataAccessFor.pods(this);
        Pod pod = carPodsDatabase.getFirstPodInDatabase();
        populateActionBar(pod);
        setPodHomeLocation(pod.getHomeLocation());
        setDepartureTime(pod.getDepartureTime());
        setReturnTime(pod.getReturnTime());
        setAbout(pod.getAboutPod());
        setMembers(pod.getMembers());
    }

    private void setMembers(List<Person> members) {
        LinearLayout podMemberLayout = (LinearLayout)findViewById(R.id.member_layout);
        for (Person memberToAdd : members) {
            TextView memberNameView = new TextView(this);
            // FIXME - there's a better way to do this with a resources file for text size
            // FIXME - for more information, see: http://stackoverflow.com/questions/9494037/how-to-set-text-size-of-textview-dynamically-for-diffrent-screens
            memberNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            memberNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            memberNameView.setText(memberToAdd.getFirstName() + " " + memberToAdd.getLastName());
            podMemberLayout.addView(memberNameView);
        }
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