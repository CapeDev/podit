package com.thoughtworks.carpods.UI;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Person;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Any;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class EditPersonTest {

    @Test
    public void shouldSavePersonToDatabase() {
        CarPodsDatabase carPodsDatabase = mock(CarPodsDatabase.class);
        EditPerson editPerson = new EditPerson(carPodsDatabase);
        editPerson.onCreate(null);
        editPerson.savePerson(null);
        verify(carPodsDatabase, times(1)).savePerson(any(Person.class));
    }

    @Test
    public void shouldGetPersonFromTheView(){
        CarPodsDatabase carPodsDatabase = mock(CarPodsDatabase.class);
        EditPerson editPerson = spy(new EditPerson(carPodsDatabase));
        editPerson.onCreate(null);
        editPerson.savePerson(null);


        String firstName = "Bob";
        String lastName = "Murray";
        String homeLocation = "Home";
        String aboutMe = "about me, yo!";

        when(editPerson.getFirstNameFromView()).thenReturn(firstName);
        when(editPerson.getLastNameFromView()).thenReturn(lastName);
        when(editPerson.getHomeLocationFromView()).thenReturn(homeLocation);
        when(editPerson.getAboutMeFromView()).thenReturn(aboutMe);

        Person actualPerson = editPerson.getDataFromView();

        assertThat(actualPerson.getFirstName(), is(firstName));
        assertThat(actualPerson.getLastName(), is(lastName));
        assertThat(actualPerson.getHomeLocation(), is(homeLocation));
        assertThat(actualPerson.getAboutMe(), is(aboutMe));
    }


}
