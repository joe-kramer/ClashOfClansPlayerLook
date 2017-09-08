package com.joekramer.clashofclansplayerlook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {
    @Bind(R.id.playerTitleTextView) TextView mPlayerTitleTextView;

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
    }
}
