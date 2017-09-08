package com.joekramer.clashofclansplayerlook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {
    @Bind(R.id.playerTitleTextView) TextView mPlayerTitleTextView;
    @Bind(R.id.toClanMembersListButton) Button mToClanMembersListButton;
    private String[] members = new String[] {
            "Ryan Moloney",
            "Sam Kramer",
            "Kyle Miyahara",
            "Michael Parrot",
            "Adam Johnson",
            "Bryan Langdal",
            "Joe Kramer"
    };

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
        String playerCode = intent.getStringExtra("playerCode");
        mPlayerTitleTextView.setText(playerCode);

        mToClanMembersListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send to ClanMemberListView activity
                Intent intent = new Intent(PlayerActivity.this, ClanMembersListActivity.class);
                intent.putExtra("members", members);
                startActivity(intent);
            }
        });
    }
}
