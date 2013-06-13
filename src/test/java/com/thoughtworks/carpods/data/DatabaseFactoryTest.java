package com.thoughtworks.carpods.data;

import android.content.Context;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class DatabaseFactoryTest {

    private DatabaseFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new DatabaseFactory();
    }

    @Test
    public void shouldReturnAnInstanceOfDatabase() throws Exception {
        assertThat(factory.instance(mock(Context.class)), is(instanceOf(PodItDatabase.class)));
    }
}
