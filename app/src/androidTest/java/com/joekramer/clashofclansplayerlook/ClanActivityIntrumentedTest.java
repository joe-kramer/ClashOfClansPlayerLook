package com.joekramer.clashofclansplayerlook;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.joekramer.clashofclansplayerlook.ui.ClanActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;


public class ClanActivityIntrumentedTest {
//    @Rule
//    public ActivityTestRule<ClanActivity> activityTestRule = new ActivityTestRule<>(ClanActivity.class);
//
//    @Test
//    public void listItemClickDisplaysToastWithCorrectMember() {
//        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
//        String memberName = "Ryan Moloney";
//        onData(anything())
//                .inAdapterView(withId(R.id.clanMembersListView))
//                .atPosition(0)
//                .perform(click());
//        onView(withText(memberName)).inRoot(withDecorView(not(activityDecorView)))
//                .check(matches(withText(memberName)));
//    }
}
