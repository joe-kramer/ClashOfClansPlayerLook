package com.joekramer.clashofclansplayerlook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.lookupPlayerButton) Button mLookupPlayerButton;
    @Bind(R.id.playerCodeEditText) EditText mPlayerCodeEditText;

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
                String playerCode = mPlayerCodeEditText.getText().toString();
                Log.d(TAG, playerCode);

                //send to player activity
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("playerCode", playerCode);
                startActivity(intent);
            }
        });
    }
}
