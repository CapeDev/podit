package com.thoughtworks.carpods.UI.people;

import android.widget.EditText;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class EditPersonTest {

    @Mock private PeopleDataAccess dataAccess;

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
        activity = new EditPerson(dataAccess);
        activity.onCreate(null);
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
        viewOf(R.id.first_name_input, EditText.class).setText(bob.getFirstName());
        viewOf(R.id.last_name_input, EditText.class).setText(bob.getLastName());
        viewOf(R.id.home_location_input, EditText.class).setText(bob.getHomeLocation());
        viewOf(R.id.about_me_input, EditText.class).setText(bob.getAboutMe());
    }

    private <T> T viewOf(int viewId, Class<T> type) {
        return type.cast(activity.findViewById(viewId));
    }
}