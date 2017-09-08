package com.joekramer.clashofclansplayerlook;


import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class ClanMembersListActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<ClanMembersListActivity> activityTestRule = new ActivityTestRule<>(ClanMembersListActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectMember() {

    }
}
