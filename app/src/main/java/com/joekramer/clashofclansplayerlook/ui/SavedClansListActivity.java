package com.joekramer.clashofclansplayerlook.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.adapters.FirebaseClanViewHolder;
import com.joekramer.clashofclansplayerlook.models.Clan;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedClansListActivity extends AppCompatActivity {
    private DatabaseReference mClanReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.savedClansRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_clans_list);
        ButterKnife.bind(this);

        mClanReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CLANS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Clan, FirebaseClanViewHolder>(Clan.class, R.layout.clan_list_item, FirebaseClanViewHolder.class, mClanReference) {
            @Override
            protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, Object model, int position) {

            }

         }
    }
}
