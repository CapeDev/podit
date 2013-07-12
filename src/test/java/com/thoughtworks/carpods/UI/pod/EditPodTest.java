package com.thoughtworks.carpods.UI.pod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.*;
import com.thoughtworks.carpods.plumb.AndroidModule;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.thoughtworks.carpods.UI.pod.EditPod.PICK_CONTACT_REQUEST;
import static com.thoughtworks.carpods.fun.ViewCast.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class EditPodTest {

    @Inject
    EditPod activity;

    @Mock
    private PeopleDataAccess peopleDataAccess;
    @Mock
    private PodDataAccess podDataAccess;
    @Mock
    private DatabaseFactory databaseFactory;
    @Mock
    private DataAccessFactory dataAccessFactory;

    @Before
    public void setUp() {
        initMocks(this);
        given(dataAccessFactory.people(any(Context.class))).willReturn(peopleDataAccess);
        given(dataAccessFactory.pods(any(Context.class))).willReturn(podDataAccess);
        ObjectGraph.create(new AndroidModule(Robolectric.application), new TestModule()).inject(this);

        // FIXME - Can we instantiate the activity by using Robolectric.buildActivity() to avoid the warning in the build?
        activity.onCreate(new Bundle());
    }

    @Test
    public void shouldStartThePeopleListActivityWhenAddingAMemberIconIsClicked() {
        imageView(activity, R.id.add_member).performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        ShadowActivity.IntentForResult activityForResult = shadowActivity.getNextStartedActivityForResult();
        assertThat(activityForResult.requestCode, is(equalTo(PICK_CONTACT_REQUEST)));
    }

    @Test
    public void shouldAddPersonToEditPodViewAfterSelectingAContactFromPersonList() {

        Person bob = createBob();
        given(peopleDataAccess.getPersonFromDatabaseWithId(any(Long.class))).willReturn(bob);

        activity.onActivityResult(PICK_CONTACT_REQUEST, RESULT_OK, new Intent());

        LinearLayout memberLayout = (LinearLayout)activity.findViewById(R.id.member_list);
        TextView memberView = (TextView)memberLayout.getChildAt(0);

        assertThat(memberView.getText().toString(), is(bob.getFirstName() + " " + bob.getLastName()));
    }

    @Test
    public void shouldSavePodToDatabase() {
        Person bob = createBob();
        List<Person> members = new ArrayList<Person>();
        members.add(bob);

        Person alice = createAlice();
        members.add(alice);

        Pod pod = createPodInformation(members);

        editText(activity, R.id.pod_name).setText(pod.getName());
        // FIXME - setting the departure and return time as static strings is a disconnect between here and Pod creation in this test class
        textView(activity, R.id.departure_time).setText("7:00");
        textView(activity, R.id.return_time).setText("18:00");
        editText(activity, R.id.about_pod).setText(pod.getAboutPod());

        given(peopleDataAccess.getPersonFromDatabaseWithId(any(Long.class))).willReturn(bob);
        Intent bobIntent = new Intent();
        bobIntent.putExtra("personId", bob.getId());
        activity.onActivityResult(PICK_CONTACT_REQUEST, RESULT_OK, bobIntent);

        given(peopleDataAccess.getPersonFromDatabaseWithId(any(Long.class))).willReturn(alice);
        Intent aliceIntent = new Intent();
        aliceIntent.putExtra("personId", alice.getId());
        activity.onActivityResult(PICK_CONTACT_REQUEST, RESULT_OK, aliceIntent);

        activity.save(null);
        verify(podDataAccess, times(1)).savePod(refEq(pod));
    }

    @Test
    public void shouldCallFinishWhenHomeIdIsSelectedFromTheOptionsMenu() {
        int homeId = android.R.id.home;
        MenuItem menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(homeId);
        activity.onOptionsItemSelected(menuItem);
        assertThat(activity.isFinishing(), is(true));
    }

    private Pod createPodInformation(List<Person> members) {
        Pod.Builder podBuilder = new Pod.Builder()
                .name("My New Pod")
                .homeLocation("Residence Inn")
                .departureTime(700)
                .returnTime(1800)
                .about("about the new pod")
                .members(members);
        return podBuilder.build();
    }

    private Person createBob() {
        return new Person.Builder()
                .firstName("Bob")
                .lastName("Murray")
                .homeLocation("Renaissance")
                .aboutMe("Just an awesome person")
                .picture("")
                .id(1)
                .build();
    }

    private Person createAlice() {
        return new Person.Builder()
                .firstName("Alice")
                .lastName("Wonderland")
                .homeLocation("Renaissance")
                .aboutMe("Likes to Eat and Drink things")
                .picture("")
                .id(2)
                .build();
    }

    @Module(
            includes = AndroidModule.class,
            injects = {EditPod.class, EditPodTest.class},
            overrides = true
    )
    class TestModule {
        @Provides
        DataAccessFactory provideDataAccessFactory() {
            return dataAccessFactory;
        }
    }
}