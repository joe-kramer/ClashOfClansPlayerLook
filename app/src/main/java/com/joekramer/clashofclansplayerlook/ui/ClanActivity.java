package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    @Bind(R.id.playerTitleTextView) TextView mPlayerTitleTextView;
    @Bind(R.id.clanMembersListView) ListView mClanMembersListView;
    public Clan mClan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        //font
        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        mPlayerTitleTextView.setTypeface(titleFont);

        //set title
        Intent intent = getIntent();
        String clanTag = intent.getStringExtra("clanTag");
        mPlayerTitleTextView.setText(clanTag);


//        //set toast on list item click
//        mClanMembersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String member = ((TextView) view).getText().toString();
//                Toast.makeText(ClanActivity.this, member, Toast.LENGTH_SHORT).show();
//            }
//        });

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
                    if(response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        //TODO could just put jsonData as response
                        mClan = CocService.processClanResults(response, jsonData);
                        Log.v(TAG, mClan.mName);

                        //put response back onto main thread
                        ClanActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set info that you want user to see
                                String[] memberNames = new String[mClan.mMemberList.size()];
                                for(int i = 0; i < mClan.mMemberList.size(); i++) {
                                    memberNames[i] = mClan.mMemberList.get(i).getName();
                                }
                                ArrayAdapter adapter = new ArrayAdapter(ClanActivity.this, android.R.layout.simple_list_item_1, memberNames);
                                mClanMembersListView.setAdapter(adapter);
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
