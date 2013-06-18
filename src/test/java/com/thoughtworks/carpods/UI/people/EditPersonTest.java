package com.thoughtworks.carpods.UI.people;

import android.content.Context;
import android.os.Bundle;
import com.thoughtworks.carpods.plumb.AndroidModule;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.DatabaseFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;
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

import static com.thoughtworks.carpods.UI.people.EditPerson.SELECT_PICTURE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class EditPersonTest {

    @Mock private PeopleDataAccess dataAccess;
    @Mock private DatabaseFactory databaseFactory;
    @Mock private DataAccessFactory dataAccessFactory;

    @Inject EditPerson activity;

    private ViewCast viewCast;
    private Person bob = new Person.Builder()
            .firstName("Bob")
            .lastName("Murray")
            .homeLocation("Renaissance")
            .aboutMe("Just an awesome person")
            .picture("")
            .build();

    @Before
    public void setUp() {
        initMocks(this);
        given(dataAccessFactory.people(any(Context.class))).willReturn(dataAccess);

        ObjectGraph.create(new AndroidModule(Robolectric.application), new TestModule()).inject(this);

        activity.onCreate(new Bundle());
        viewCast = new ViewCast(activity);
    }

    @Test
    public void shouldSavePersonToDatabase() {
        addBobToView();
        activity.save(null);
        verify(dataAccess, times(1)).savePerson(refEq(bob));
    }

    @Test
    public void shouldCallFinishAfterSavingPerson(){
        activity.save(null);
        assertThat(activity.isFinishing(), is(true));
    }

    @Test
    public void shouldOpenGallerySoUserCanPickAnPicture() throws Exception {
        viewCast.imageButton(R.id.profile_picture).performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        ShadowActivity.IntentForResult activityForResult = shadowActivity.getNextStartedActivityForResult();

        assertThat(activityForResult.requestCode, is(equalTo(SELECT_PICTURE)));
    }

    private void addBobToView() {
        viewCast.editText(R.id.first_name_input).setText(bob.getFirstName());
        viewCast.editText(R.id.first_name_input).setText(bob.getFirstName());
        viewCast.editText(R.id.last_name_input).setText(bob.getLastName());
        viewCast.editText(R.id.home_location_input).setText(bob.getHomeLocation());
        viewCast.editText(R.id.about_me_input).setText(bob.getAboutMe());
    }

    @Module(
            includes = AndroidModule.class,
            injects = {EditPerson.class, EditPersonTest.class},
            overrides = true
    )
    class TestModule {
        @Provides
        DataAccessFactory provideDataAccessFactory() {
            return dataAccessFactory;
        }
    }
}