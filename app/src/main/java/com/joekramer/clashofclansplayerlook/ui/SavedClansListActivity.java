package com.joekramer.clashofclansplayerlook.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    //reuse memberList recyclerView
    @Bind(R.id.memberListRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ButterKnife.bind(this);

        //get all saved clans under this users node
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mClanReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CLANS)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Clan, FirebaseClanViewHolder>(Clan.class, R.layout.clan_list_item, FirebaseClanViewHolder.class, mClanReference) {

            @Override
            protected void populateViewHolder(FirebaseClanViewHolder viewHolder, Clan model, int position) {
                viewHolder.bindClan(model);
            }
         };
         mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
