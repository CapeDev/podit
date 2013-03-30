package com.thoughtworks.carpods;

import android.widget.EditText;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//example testclass based on Kris Gonzalez's myTW screencast
@RunWith(RobolectricTestRunner.class)
public class HelloAndroidActivityTest {

    HelloAndroidActivity activity;
    private EditText editTextView;

    @Before
    public void setUp(){
        activity = new HelloAndroidActivity();
        activity.onCreate(null);

        editTextView = (EditText)activity.findViewById(R.id.edit_message_field);
    }

    @Test
    public void shouldExecuteLongRunningOperationWithSelf(){
        activity.operationExecutor = mock(LongRunningOperationExecutor.class);
        activity.onResume();
        verify(activity.operationExecutor).execute(activity);
    }

    @Test
    public void shouldUpdateTextViewWithNewTextOnButtonClick() {
        String testMessageOne = "hey hey hey,";
        editTextView.setText(testMessageOne);
        activity.updateMessage(null);
        TextView messageField = (TextView)activity.findViewById(R.id.message_field);
        assertEquals(testMessageOne, messageField.getText().toString());
    }

    @Test
    public void shouldClearEditFieldOnButtonClick() {
        String testMessageTwo = "...gooooodbye....";
        editTextView.setText(testMessageTwo);
        activity.updateMessage(null);
        assertEquals("", editTextView.getText().toString());
    }
}