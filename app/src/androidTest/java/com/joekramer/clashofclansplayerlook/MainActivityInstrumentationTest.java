package com.joekramer.clashofclansplayerlook;

import android.support.test.rule.ActivityTestRule;

import com.joekramer.clashofclansplayerlook.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        //check EditText works
        onView(withId(R.id.playerCodeEditText)).perform(typeText("#123456"))
                .check(matches(withText("#123456")));

        //check that playerCode displays as activity_player page title
        onView(withId(R.id.lookupPlayerButton)).perform(click());
        onView(withId(R.id.playerTitleTextView)).check(matches(withText("#123456")));
    }

    @Test
    public void playerCodeIsPlayerActivityTitle() {
        onView(withId(R.id.playerCodeEditText)).perform(typeText("#123456"));

        onView(withId(R.id.lookupPlayerButton)).perform(click());
        onView(withId(R.id.playerTitleTextView)).check(matches(withText("#123456")));
    }
}
