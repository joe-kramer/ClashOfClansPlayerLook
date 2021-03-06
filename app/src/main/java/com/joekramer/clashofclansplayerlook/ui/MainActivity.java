package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.joekramer.clashofclansplayerlook.R;

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

    //Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //greeting message
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

        //font
        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        mTitleTextView.setTypeface(titleFont);

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

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //implicit intent
        SpannableString content = new SpannableString("https://github.com/joe-kramer");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mImplicitTextView.setText(content);
        mImplicitTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLookupClanButton) {
            String clanTag = mClanTagEditText.getText().toString();
            Log.d(TAG, clanTag);

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

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
