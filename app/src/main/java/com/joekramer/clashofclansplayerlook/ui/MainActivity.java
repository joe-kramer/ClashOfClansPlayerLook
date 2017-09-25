package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.textValidation.TextValidator;

import butterknife.Bind;
import butterknife.ButterKnife;
//TODO: implement OnClick Interface (week 1 tuesday)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.lookupClanButton) Button mLookupClanButton;
    @Bind(R.id.clanTagEditText) EditText mClanTagEditText;
    @Bind(R.id.implicitTextView) TextView mImplicitTextView;
    @Bind(R.id.savedClansListButton) Button mSavedClansListButton;

    //shared preferences
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentClanTag;

    //firebase
    private DatabaseReference mSearchedClanReference;
    private ValueEventListener mSearchedClanReferenceListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //font
        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        mTitleTextView.setTypeface(titleFont);

        //shared preference
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        //test
//        mRecentClanTag = mSharedPreferences.getString(Constants.PREFERENCES_CLANTAG_KEY, null);
//        try {
//            Log.d("Shared Pref Location", mRecentClanTag);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }

        //database
        mSearchedClanReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_CLANS);

        mSearchedClanReferenceListener = mSearchedClanReference.addValueEventListener(new ValueEventListener() { //attach listener
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot clanTagSnapshot : dataSnapshot.getChildren()) {
                    String clanTag = clanTagSnapshot.getValue().toString();
                    Log.d("ClanTag updated", "clanTag: " + clanTag); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //update UI here if error occurred.
            }
        });

        mLookupClanButton.setOnClickListener(this);
        mSavedClansListButton.setOnClickListener(this);

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

        //implicit intent
        SpannableString content = new SpannableString("https://github.com/joe-kramer");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mImplicitTextView.setText(content);
        mImplicitTextView.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedClanReference.removeEventListener(mSearchedClanReferenceListener);
    }

    @Override
    public void onClick(View v) {
        if (v == mLookupClanButton) {
            String clanTag = mClanTagEditText.getText().toString();
            Log.d(TAG, clanTag);

            saveClanToFirebase(clanTag);

//            add to shared preferences
//            if(!(clanTag).equals("")) {
//                addToSharedPreferences(clanTag);
//            }

            //send to player activity
            Intent intent = new Intent(MainActivity.this, ClanActivity.class);
            intent.putExtra("clanTag", clanTag);
            startActivity(intent);
        }
        if (v == mImplicitTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mImplicitTextView.getText().toString()));
            startActivity(webIntent);
        }
        if (v == mSavedClansListButton) {
            Intent intent = new Intent(MainActivity.this, SavedClansListActivity.class);
            startActivity(intent);
        }
    }

//    private void addToSharedPreferences(String clanTag) {
//        mEditor.putString(Constants.PREFERENCES_CLANTAG_KEY, clanTag)
//                .apply();
//    }

    private void saveClanToFirebase(String clanTag) {
        mSearchedClanReference.push().setValue(clanTag);
    }
}
