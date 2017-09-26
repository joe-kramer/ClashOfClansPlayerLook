package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.adapters.MemberListAdapter;
import com.joekramer.clashofclansplayerlook.models.Member;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberListActivity extends AppCompatActivity {
    public static final String TAG = MemberListActivity.class.getSimpleName();
    private ArrayList<Member> memberList;

    @Bind(R.id.memberListRecyclerView) RecyclerView mMemberListRecyclerView;
    private MemberListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ButterKnife.bind(this);

        memberList = this
                .getIntent()
                .getBundleExtra("membersBundle")
                .getParcelableArrayList("members");
        Log.v(TAG, memberList.toString());

        mAdapter = new MemberListAdapter(getApplicationContext(), memberList);
        mMemberListRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MemberListActivity.this);
        mMemberListRecyclerView.setLayoutManager(layoutManager);
        mMemberListRecyclerView.setHasFixedSize(true);
    }

    //TODO: ADD members to fragment view
}
