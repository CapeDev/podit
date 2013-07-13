package com.thoughtworks.carpods.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.thoughtworks.carpods.data.development.Populator;
import com.thoughtworks.carpods.data.development.PopulatorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ActivitySwitcherTest {

    //@Inject
    ActivitySwitcher activity;

    @Test
    public void shouldPopulateSomePodsOnCreate() {
        activity = new ActivitySwitcher();
        activity.setIntent(new Intent());

        PopulatorFactory mockPopulatorFactory = createMockPopulatorFactory(mock(Populator.class), mock(Populator.class));
        activity.populatorFactory = mockPopulatorFactory;

        activity.onCreate( new Bundle());

        verify(mockPopulatorFactory).createPeoplePopulator(any(Context.class));
        //check it actually does stuff with the people populator
        // do the same with the pod populator
    }



    private PopulatorFactory createMockPopulatorFactory(Populator peoplePopulator, Populator podPopulator) {
        PopulatorFactory mockPopulatorFactory = mock(PopulatorFactory.class);
        when( mockPopulatorFactory.createPeoplePopulator(any(Context.class)) ).thenReturn(peoplePopulator);
        when( mockPopulatorFactory.createPodPopulator(any(Context.class)) ).thenReturn(podPopulator);
        return mockPopulatorFactory;
    }
}