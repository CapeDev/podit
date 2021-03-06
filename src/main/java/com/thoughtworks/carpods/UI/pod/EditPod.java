package com.thoughtworks.carpods.UI.pod;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.people.PeopleList;
import com.thoughtworks.carpods.data.*;
import com.thoughtworks.carpods.plumb.PodActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

// FIXME - what if the activity is editing a pod instead of creating a new one?
public class EditPod extends PodActivity {

    @Inject
    DataAccessFactory dataAccessFor;

    protected static final int PICK_CONTACT_REQUEST = 1;

    private String CLAZZ_TAG = "EditPod";
    private List<Person> podMembers = new ArrayList<Person>();
    private boolean edit;
    private Pod pod;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_pod);
        populateView();
        setUpActionBar();

        Log.v(CLAZZ_TAG, "Done with onCreate in EditPod");
    }

    private void populateView() {
        Long id = getIntent().getLongExtra("id", 0L);
        if (id > 0) {
            edit = true;
            pod = dataAccessFor.pods(this).getPodFromDatabaseWithId(id);
            ((TextView)findViewById(R.id.pod_name)).setText(pod.getName());
            ((TextView)findViewById(R.id.about_pod)).setText(pod.getAboutPod());
        }
    }

    public void save(MenuItem item) {
        showToast("I'm saving a pod from the menu!");
        Pod pod = buildPodFromFormWithId(this.pod == null ? -1L : this.pod.getId());
        PodDataAccess podDataAccess = dataAccessFor.pods(this);
        if (edit) {
            podDataAccess.updatePod(pod);
        } else {
            podDataAccess.savePod(pod);
        }
        finish();
    }

    private Pod buildPodFromFormWithId(Long id) {
        return new Pod.Builder().id(id)
                                .name(getPodNameFromView())
                                .homeLocation(getHomeLocationFromView())
                                .departureTime(getDepartureTimeFromView())
                                .returnTime(getReturnTimeFromView())
                                .about(getAboutFromView())
                                .members(podMembers)
                                .build();
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

    public void addMemberClick(View view) {
        Intent intent = new Intent(this, PeopleList.class);
        intent.putExtra("sourceActivity", this.getClass().getSimpleName());
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CONTACT_REQUEST) {
                long personId = data.getIntExtra("personId", -1);
                PeopleDataAccess personDataAccess = dataAccessFor.people(this);
                Person newPodMember = personDataAccess.getPersonFromDatabaseWithId(personId);
                addMemberToMemberList(newPodMember);
                addMemberToView(newPodMember);
            }
        }
    }

    private void addMemberToMemberList(Person newPodMember) {
        podMembers.add(newPodMember);
    }

    private void addMemberToView(Person memberToAdd) {
        LinearLayout memberLayout = (LinearLayout) findViewById(R.id.member_list);
        TextView memberNameView = new TextView(this);
        memberNameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        memberNameView.setText(memberToAdd.getFirstName() + " " + memberToAdd.getLastName());
        memberLayout.addView(memberNameView);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_action_bar, menu);
        return true;
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