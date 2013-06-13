package com.thoughtworks.carpods.UI.people;

import android.content.Context;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class EditPersonTest {

    @Mock private PeopleDataAccess dataAccess;
    @Mock private DataAccessFactory dataAccessFactory;

    private ViewCast viewCast;
    private EditPerson activity;
    private Person bob = new Person.Builder()
            .firstName("Bob")
            .lastName("Murray")
            .homeLocation("Renaissance")
            .aboutMe("Just an awesome person")
            .build();

    @Before
    public void setUp() {
        initMocks(this);
        given(dataAccessFactory.people(any(Context.class))).willReturn(dataAccess);

        activity = new EditPerson();
        activity.dataAccessFor = dataAccessFactory;
        activity.onCreate(null);
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

    private void addBobToView() {
        viewCast.editText(R.id.first_name_input).setText(bob.getFirstName());
        viewCast.editText(R.id.first_name_input).setText(bob.getFirstName());
        viewCast.editText(R.id.last_name_input).setText(bob.getLastName());
        viewCast.editText(R.id.home_location_input).setText(bob.getHomeLocation());
        viewCast.editText(R.id.about_me_input).setText(bob.getAboutMe());
    }
}