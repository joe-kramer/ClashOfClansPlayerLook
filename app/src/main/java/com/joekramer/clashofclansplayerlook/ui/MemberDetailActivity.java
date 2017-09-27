package com.joekramer.clashofclansplayerlook.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.adapters.MemberPagerAdapter;
import com.joekramer.clashofclansplayerlook.models.Member;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private MemberPagerAdapter adapterViewPager;
    ArrayList<Member> mMembers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        ButterKnife.bind(this);

        mMembers = Parcels.unwrap(getIntent().getParcelableExtra("members"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MemberPagerAdapter(getSupportFragmentManager(), mMembers);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
