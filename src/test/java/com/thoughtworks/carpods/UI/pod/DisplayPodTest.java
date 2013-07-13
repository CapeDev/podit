package com.thoughtworks.carpods.UI.pod;

import android.content.Context;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.DatabaseFactory;
import com.thoughtworks.carpods.data.PodDataAccess;
import com.thoughtworks.carpods.plumb.AndroidModule;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class DisplayPodTest {

    @Inject
    DisplayPod displayPodActivity;

    @Mock
    private DatabaseFactory databaseFactory;
    @Mock
    private DataAccessFactory dataAccessFactory;
    @Mock
    PodDataAccess podDataAccess;
//    @Mock
//    PeopleDataAccess peopleDataAccess;

//    @Before
//    public void setUp() {
//        initMocks(this);
////        given(dataAccessFactory.people(any(Context.class))).willReturn(peopleDataAccess);
//        given(dataAccessFactory.pods(any(Context.class))).willReturn(podDataAccess);
//        ObjectGraph.create(new AndroidModule(Robolectric.application), new TestModule()).inject(this);
//
//        displayPodActivity.onCreate(new Bundle());
//    }

    @Test
    public void shouldDisplayTheMembersOfAPod() {
        initMocks(this);

        given(dataAccessFactory.pods(any(Context.class))).willReturn(podDataAccess);
        ObjectGraph.create(new AndroidModule(Robolectric.application), new TestModule()).inject(this);

//        displayPodActivity.onCreate(new Bundle());
//
//        Pod testPod = new Pod.Builder()
//                .name("A Test Pod")
//                .homeLocation("A Hotel")
//                .departureTime(700)
//                .returnTime(1800)
//                .about("we're just testing stuff")
//                .members(null)
////                .members(memberList)
//                .build();

//        when(podDataAccess.getFirstPodInDatabase()).thenReturn(testPod);

        assertThat(true, is(true));
    }

    @Module(
            includes = AndroidModule.class,
            injects = {DisplayPod.class, DisplayPodTest.class},
            overrides = true
    )

    class TestModule {
        @Provides
        DataAccessFactory provideDataAccessFactory() {
            return dataAccessFactory;
        }
    }
}

//        List<Integer> memberIds = Arrays.asList(1, 2);
//        when(podMemberDataAccess.getMemberIdsForPod(0)).thenReturn(memberIds);

//        Person memberOne = new Person.Builder()
//                .firstName("Billy")
//                .lastName("Dee")
//                .aboutMe("I sing")
//                .homeLocation("Mars")
//                .picture("")
//                .id(0)
//                .build();
//
//        Person memberTwo = new Person.Builder()
//                .firstName("Harry")
//                .lastName("Ape")
//                .aboutMe("I like bananas")
//                .homeLocation("The Jungle")
//                .picture("")
//                .id(1)
//                .build();
//
//        List<Person> memberList = Arrays.asList(memberOne, memberTwo);
