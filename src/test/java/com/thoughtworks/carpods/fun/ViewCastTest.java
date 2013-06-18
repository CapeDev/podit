package com.thoughtworks.carpods.fun;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class ViewCastTest {

    public static final int AN_ID = 1;
    @Mock private Activity activity;

    private ViewCast viewCast;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        viewCast = new ViewCast(activity);
    }

    @Test
    public void shouldCastViewToEditText() throws Exception {
        mockFindViewFor(EditText.class);
        assertThat(viewCast.editText(AN_ID), is(instanceOf(EditText.class)));
    }

    @Test
    public void shouldCastViewToTextView() throws Exception {
        mockFindViewFor(TextView.class);
        assertThat(viewCast.textView(AN_ID), is(instanceOf(TextView.class)));
    }

    @Test
    public void shouldCastViewToImageButton() throws Exception {
        mockFindViewFor(ImageButton.class);
        assertThat(viewCast.imageButton(AN_ID), is(instanceOf(ImageButton.class)));
    }

    @Test
    public void shouldCastViewToImageView() throws Exception {
        mockFindViewFor(ImageView.class);
        assertThat(viewCast.imageView(AN_ID), is(instanceOf(ImageView.class)));
    }

    private <T extends View> void mockFindViewFor(Class<T> clazz) {
        given(activity.findViewById(eq(AN_ID))).willReturn(mock(clazz));
    }
}
