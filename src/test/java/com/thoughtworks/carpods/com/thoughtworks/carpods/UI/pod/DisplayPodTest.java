package com.thoughtworks.carpods.com.thoughtworks.carpods.UI.pod;

import com.thoughtworks.carpods.UI.pod.DisplayPod;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.widget.EditText;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class DisplayPodTest {

    @Test
    public void shouldGetHoursOfMilitaryTimeString(){
        DisplayPod pod = new DisplayPod();
        int hourOfHourBeforeNoon = pod.hourOf(800);
        int hourOfHourAfterNoon = pod.hourOf(1600);
        assertThat(hourOfHourBeforeNoon, is(8));
        assertThat(hourOfHourAfterNoon, is(16));
    }

    @Test
    public void shouldGetMinutesOfMilitaryTimeString(){
        DisplayPod pod = new DisplayPod();
        assertThat(pod.minuteOf(800), is(0));
        assertThat(pod.minuteOf(1645), is(45));
    }
}
