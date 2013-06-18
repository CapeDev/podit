package com.thoughtworks.carpods.UI.people;

import android.content.Intent;
import android.widget.TextView;

import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class DisplayPersonTest {

    @Test
    public void shouldSetFullNameFieldToPersonsName() throws Exception {
        Person person = new Person.Builder()
                .firstName("Dirt").lastName("McGirt").aboutMe("I'm Old and Dirty").homeLocation("Shaolin").build();
        PeopleDataAccess dataAccess = mock(PeopleDataAccess.class);
        when(dataAccess.getFirstPersonFromDatabase()).thenReturn(person);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.hasExtra("id")).thenReturn(false);

        DisplayPerson displayPerson = new DisplayPerson(dataAccess, mockIntent);
        displayPerson.onCreate(null);

        displayPerson.setPersonName();

        TextView fullnameField = new ViewCast(displayPerson).textView(R.id.full_name_field);
        assertThat(fullnameField.getText().toString(),
                is(equalTo(person.getFirstName() + " " + person.getLastName())));
    }
}
