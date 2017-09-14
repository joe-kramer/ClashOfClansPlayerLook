package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.joekramer.clashofclansplayerlook.R;

import butterknife.Bind;
import butterknife.ButterKnife;
//TODO: implement OnClick Interface (week 1 tuesday)
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.lookupPlayerButton) Button mLookupPlayerButton;
    @Bind(R.id.clanTagEditText) EditText mClanTagEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //font
        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        mTitleTextView.setTypeface(titleFont);

        //player lookup button
        mLookupPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clanTag = mClanTagEditText.getText().toString();
                Log.d(TAG, clanTag);

                //send to player activity
                Intent intent = new Intent(MainActivity.this, ClanActivity.class);
                intent.putExtra("clanTag", clanTag);
                startActivity(intent);
            }
        });
    }
}
