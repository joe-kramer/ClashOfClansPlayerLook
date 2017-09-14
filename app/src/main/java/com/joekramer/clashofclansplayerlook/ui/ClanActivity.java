package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.joekramer.clashofclansplayerlook.adapters.MemberListAdapter;
import com.joekramer.clashofclansplayerlook.models.Clan;
import com.joekramer.clashofclansplayerlook.services.CocService;
import com.joekramer.clashofclansplayerlook.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClanActivity extends AppCompatActivity {
    public static final String TAG = ClanActivity.class.getSimpleName();
//    @Bind(R.id.playerTitleTextView) TextView mPlayerTitleTextView;


    //for memberList recycleView
    @Bind(R.id.memberListRecyclerView) RecyclerView mMemberListRecyclerView;
    private MemberListAdapter mAdapter;
    public Clan mClan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);
        ButterKnife.bind(this);

        //get clanTag
        Intent intent = getIntent();
        String clanTag = intent.getStringExtra("clanTag");

        //set clan
        getClanInfo(clanTag);

        //font
//        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
//        mPlayerTitleTextView.setTypeface(titleFont);

        //set title
//        mPlayerTitleTextView.setText(clanTag);
    }

    private void getClanInfo(String clanTag) {
        final CocService cocService = new CocService();
        cocService.findClan(clanTag, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if(response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        //TODO could just put jsonData as response
                        mClan = CocService.processClanResults(response, jsonData);
                        Log.v(TAG, mClan.mName);

                        //put response back onto main thread
                        ClanActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set MemberListRecycleView
                                mAdapter = new MemberListAdapter(getApplicationContext(), mClan.mMemberList);
                                mMemberListRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClanActivity.this);
                                mMemberListRecyclerView.setLayoutManager(layoutManager);
                                mMemberListRecyclerView.setHasFixedSize(true);
//                                Log.v(TAG, mClan.mTag);
//                                Log.v(TAG, mClan.mName);
//                                Log.v(TAG, mClan.mDescription);
//                                Log.v(TAG, mClan.mLocationName);
//                                Log.v(TAG, mClan.mBadgeUrl);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
