package com.thoughtworks.carpods;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//Example testclass to follow Kris Gonzalez's screencast on myTW
public class LongRunningOperationExecutorTest {

    @Test
    public void shouldCallbackToActivityAfterExecution(){
        HelloAndroidActivity activity = mock(HelloAndroidActivity.class);
        new LongRunningOperationExecutor().execute(activity);

        verify(activity).callbackAfterExecution();
    }
}
