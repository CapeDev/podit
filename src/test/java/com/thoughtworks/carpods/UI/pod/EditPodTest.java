package com.thoughtworks.carpods.UI.pod;

import com.thoughtworks.carpods.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static com.thoughtworks.carpods.UI.pod.EditPod.PICK_CONTACT_REQUEST;
import static com.thoughtworks.carpods.fun.ViewCast.imageView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class EditPodTest {

    private EditPod activity;

    @Before
    public void setUp() {

        activity = Robolectric.buildActivity(EditPod.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void shouldStartThePeopleListActivityWhenAddingAMemberIconIsClicked() {
        imageView(activity, R.id.add_member).performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        ShadowActivity.IntentForResult activityForResult = shadowActivity.getNextStartedActivityForResult();
        assertThat(activityForResult.requestCode, is(equalTo(PICK_CONTACT_REQUEST)));
    }
}