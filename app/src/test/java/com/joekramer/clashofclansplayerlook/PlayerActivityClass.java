package com.joekramer.clashofclansplayerlook;


import android.os.Build;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class PlayerActivityClass {
    private PlayerActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(PlayerActivity.class);
    }

//    @Test
//    public void validateTextViewContent() {
//        TextView titleTextView = (TextView) activity.findViewById(R.id.titleTextView);
//        assertTrue("Look up Clan's stats".equals(titleTextView.getText().toString()));
//    }
}
