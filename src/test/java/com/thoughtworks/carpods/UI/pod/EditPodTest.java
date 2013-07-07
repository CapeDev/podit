package com.thoughtworks.carpods.UI.pod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.DatabaseFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
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

import static android.app.Activity.RESULT_OK;
import static com.thoughtworks.carpods.UI.pod.EditPod.PICK_CONTACT_REQUEST;
import static com.thoughtworks.carpods.fun.ViewCast.imageView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class EditPodTest {

    @Inject
    EditPod activity;

    @Mock
    private PeopleDataAccess dataAccess;
    @Mock
    private DatabaseFactory databaseFactory;
    @Mock
    private DataAccessFactory dataAccessFactory;

    @Before
    public void setUp() {
        initMocks(this);
        given(dataAccessFactory.people(any(Context.class))).willReturn(dataAccess);
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

        Person bob = new Person.Builder()
                .firstName("Bob")
                .lastName("Murray")
                .homeLocation("Renaissance")
                .aboutMe("Just an awesome person")
                .picture("")
                .build();
        given(dataAccess.getPersonFromDatabaseWithId(any(Long.class))).willReturn(bob);

        activity.onActivityResult(PICK_CONTACT_REQUEST, RESULT_OK, new Intent());

        LinearLayout memberLayout = (LinearLayout)activity.findViewById(R.id.member_list);
        TextView memberView = (TextView)memberLayout.getChildAt(0);

        assertThat(memberView.getText().toString(), is(bob.getFirstName() + " " + bob.getLastName()));
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