package com.thoughtworks.carpods.fun;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class ViewCastTest {

    @Mock private Activity activity;

    private ViewCast viewCast;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        viewCast = new ViewCast(activity);
    }

    @Test
    public void shouldCastViewToEditText() throws Exception {
        given(activity.findViewById(eq(1))).willReturn(new EditText(null));
        assertThat(viewCast.editText(1), is(instanceOf(EditText.class)));
    }

    @Test
    public void shouldCastViewToTextView() throws Exception {
        given(activity.findViewById(eq(1))).willReturn(new TextView(null));
        assertThat(viewCast.textView(1), is(instanceOf(TextView.class)));
    }
}
