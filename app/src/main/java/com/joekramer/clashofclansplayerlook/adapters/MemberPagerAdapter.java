package com.joekramer.clashofclansplayerlook.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.joekramer.clashofclansplayerlook.models.Member;
import com.joekramer.clashofclansplayerlook.ui.MemberDetailFragment;

import java.util.ArrayList;

public class MemberPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Member> mMembers;

    public MemberPagerAdapter(FragmentManager fm, ArrayList<Member> restaurants) {
        super(fm);
        mMembers = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return MemberDetailFragment.newInstance(mMembers.get(position));
    }

    @Override
    public int getCount() {
        return mMembers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMembers.get(position).getName();
    }
}
