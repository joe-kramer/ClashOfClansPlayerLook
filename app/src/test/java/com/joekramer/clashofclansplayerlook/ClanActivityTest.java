package com.joekramer.clashofclansplayerlook;


import android.os.Build;
import android.widget.ListView;

import com.joekramer.clashofclansplayerlook.ui.ClanActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class ClanActivityTest {
    private ClanActivity activity;
    private ListView mClanMembersListView;


    @Before
    public void setup() {
        activity = Robolectric.setupActivity(ClanActivity.class);
        mClanMembersListView = (ListView) activity.findViewById(R.id.clanMembersListView);

    }

    @Test
    public void clanMembersListViewPopulates() {
        assertNotNull(mClanMembersListView.getAdapter());
        assertEquals(mClanMembersListView.getAdapter().getCount(), 7);
    }
}
