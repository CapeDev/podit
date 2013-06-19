package com.thoughtworks.carpods.UI.people;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
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

import javax.inject.Inject;

import static com.thoughtworks.carpods.fun.ViewCast.textView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class DisplayPersonTest {

    @Inject DisplayPerson displayPerson;
    @Mock DataAccessFactory dataAccessFactory;
    @Mock PeopleDataAccess dataAccess;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        ObjectGraph.create(new AndroidModule(Robolectric.application), new TestModule()).inject(this);
        when(dataAccessFactory.people(any(Context.class))).thenReturn(dataAccess);
    }

    @Test
    public void shouldSetFullNameFieldToPersonsName() throws Exception {
        Person person = new Person.Builder()
                .firstName("Dirt").lastName("McGirt").aboutMe("I'm Old and Dirty").homeLocation("Shaolin").picture("").build();
        when(dataAccess.getFirstPersonFromDatabase()).thenReturn(person);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.hasExtra("id")).thenReturn(false);

        displayPerson.setIntent(mockIntent);
        displayPerson.onCreate(null);

        TextView fullNameField = textView(displayPerson, R.id.full_name_field);
        assertThat(fullNameField.getText().toString(),
                is(equalTo(person.getFirstName() + " " + person.getLastName())));
    }

    @Module(
            includes = AndroidModule.class,
            injects = {DisplayPerson.class, DisplayPersonTest.class},
            overrides = true
    )
    class TestModule {
        @Provides
        DataAccessFactory provideDataAccessFactory() {
            return dataAccessFactory;
        }
    }
}
