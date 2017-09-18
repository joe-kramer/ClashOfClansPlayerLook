package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.textValidation.TextValidator;

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

        //TODO move text Validation to own class
        //text validation
        mClanTagEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                String filtered_str = s.toString();

                if (filtered_str.matches(".*[^A-Z^0-9^#].*")) {

                    filtered_str = filtered_str.replaceAll("[^a-z^0-9]", "");

                    s.clear();

                    // s.insert(0, filtered_str);
                    //TODO set length validation
                    Toast.makeText(MainActivity.this,
                            "Hash mark, with combination of uppercase letters and numbers",
                            Toast.LENGTH_SHORT).show();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
}
