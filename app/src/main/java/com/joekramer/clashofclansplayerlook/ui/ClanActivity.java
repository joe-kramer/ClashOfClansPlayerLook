package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.joekramer.clashofclansplayerlook.adapters.MemberListAdapter;
import com.joekramer.clashofclansplayerlook.models.Clan;
import com.joekramer.clashofclansplayerlook.services.CocService;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlookup.clientsdk.GetClanInfoAPIClient;
import com.joekramer.clashofclansplayerlookup.clientsdk.model.Empty;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClanActivity extends AppCompatActivity {
    public static final String TAG = ClanActivity.class.getSimpleName();
    public Clan mClan = null;
    //for Clan info
    @Bind(R.id.clanNameTextView) TextView mClanNameTextView;
    @Bind(R.id.clanLevelTextView) TextView mClanLevelTextView;
    @Bind(R.id.clanDescriptionTextView) TextView mClanDescriptionTextView;
    @Bind(R.id.clanBadgeImageView) ImageView mClanBadgeImageView;
    @Bind(R.id.clanTagTextView) TextView mClanTagTextView;
    @Bind(R.id.clanTypeTextView) TextView mClanTypeTextView;
    @Bind(R.id.clanLocationTextView) TextView mClanLocationTextView;
    @Bind(R.id.clanRequiredTrophiesTextView) TextView mClanRequiredTrophiesTextView;

    //background
//    @Bind(R.id.picLinearLayout) LinearLayout mPicLinearLayout;

    //for memberList recycleView
//    @Bind(R.id.memberListRecyclerView) RecyclerView mMemberListRecyclerView;
//    private MemberListAdapter mAdapter;

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
                    jsonData = jsonData.trim();
                    jsonData = jsonData.substring(1, jsonData.length() - 1);
                    jsonData = jsonData.replace("\\","");
                        Log.v(TAG, jsonData);
                        //TODO could just put jsonData as response
                        mClan = CocService.processClanResults(response, jsonData);
//                        Log.v(TAG, mClan.mName);

                        //put response back onto main thread
                        ClanActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //display MemberListRecycleView
//                                mAdapter = new MemberListAdapter(getApplicationContext(), mClan.mMemberList);
//                                mMemberListRecyclerView.setAdapter(mAdapter);
//                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClanActivity.this);
//                                mMemberListRecyclerView.setLayoutManager(layoutManager);
//                                mMemberListRecyclerView.setHasFixedSize(true);

                                //CLAN INFO
                                mClanNameTextView.setText(mClan.mName);
                                mClanLevelTextView.setText(Integer.toString(mClan.mClanLevel));
                                //font
                                Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
                                mClanNameTextView.setTypeface(titleFont);
                                mClanDescriptionTextView.setText(mClan.mDescription);
//                                //clan badge pic
                                Picasso.with(getApplicationContext()).load(mClan.mBadgeUrl)
                                .into(mClanBadgeImageView);
                                mClanTagTextView.setText("Tag: " + mClan.mTag);
                                mClanTypeTextView.setText("Type: " + mClan.mType);
                                mClanRequiredTrophiesTextView.setText("Required Trophies" + mClan.mRequiredTrophies);
                                mClanLocationTextView.setText(mClan.mLocationName);
//                                mClanPoints
//                                mClanVersusPoints
//
//                                mWarWinStreak
//                                mWarWins
//                                mWarTies
//                                mWarLosses

//                                mMembers



                                //background on linear layout
//                                new LoadBackground(mClan.mBadgeUrl, "clanBackground").execute();                            }
                            }
                        });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    private class LoadBackground extends AsyncTask<String, Void, Drawable> {
//
//        private String imageUrl, imageName;
//
//        public LoadBackground(String url, String file_name) {
//            this.imageUrl = url;
//            this.imageName = file_name;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Drawable doInBackground(String... urls) {
//
//            try {
//                InputStream is = (InputStream) this.fetch(this.imageUrl);
//                Drawable d = Drawable.createFromStream(is, this.imageName);
//                return d;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        private Object fetch(String address) throws MalformedURLException, IOException {
//            URL url = new URL(address);
//            Object content = url.getContent();
//            return content;
//        }
//
//        @Override
//        protected void onPostExecute(Drawable result) {
//            super.onPostExecute(result);
//            mPicLinearLayout.setBackground(result);
//        }
//    }
}
