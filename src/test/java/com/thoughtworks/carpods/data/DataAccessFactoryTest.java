package com.thoughtworks.carpods.data;

import android.app.Activity;
import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

public class DataAccessFactoryTest {
    @Mock private Activity anActivity;
    @Mock private PodItDatabase database;
    @Mock private DatabaseFactory databaseFactory;

    private DataAccessFactory dataAccessFor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        dataAccessFor = new DataAccessFactory();
        dataAccessFor.database = databaseFactory;

        given(databaseFactory.instance(any(Context.class))).willReturn(database);
    }

    @Test
    public void shouldReturnAnInstanceOfPeopleDataAccess() throws Exception {
        assertThat(dataAccessFor.people(anActivity), is(instanceOf(PeopleDataAccess.class)));
    }
}
