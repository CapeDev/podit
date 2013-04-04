package com.thoughtworks.carpods.UI;

import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Person;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class EditPersonTest {

    private CarPodsDatabase carPodsDatabase;

    @Before
    public void setUp() {
        carPodsDatabase = mock(CarPodsDatabase.class);
    }

    @Test
    public void shouldSavePersonToDatabase() {
        EditPerson editPerson = new EditPerson(carPodsDatabase);
        editPerson.onCreate(null);
        editPerson.savePerson(null);
        verify(carPodsDatabase, times(1)).savePerson(any(Person.class));
    }

    @Test
    public void shouldGetPersonFromTheView(){
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

    @Test
    public void shouldCallFinishAfterSavingPerson(){
        EditPerson editPerson = spy(new EditPerson(carPodsDatabase));
        editPerson.onCreate(null);
        editPerson.savePerson(null);

        verify(editPerson, times(1)).finish();
    }


}
