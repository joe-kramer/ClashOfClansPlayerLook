package com.joekramer.clashofclansplayerlook.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.models.Clan;
import com.joekramer.clashofclansplayerlook.ui.ClanActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FirebaseClanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = FirebaseClanViewHolder.class.getSimpleName();
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    private String clanTag;

    public FirebaseClanViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindClan(Clan clan) {
        ImageView mClanBadgeImageView = mView.findViewById(R.id.savedClanListImageView);
        TextView mClanNameTextView = mView.findViewById(R.id.savedClanListNameTextView);
        TextView mClanLevelTextView = mView.findViewById(R.id.savedClanListClanLevelTextView);
        TextView mClanWinRatioTextView = mView.findViewById(R.id.savedClanListWinRatioTextView);

        Picasso.with(mContext)
                .load(clan.getBadgeUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mClanBadgeImageView);

        String clanName = clan.getName();
        Log.d(TAG, clanName);
        Log.d(TAG, "level: " + clan.getClanLevel());
        Log.d(TAG, "ratio: " + clan.getWinLossRatio());

        mClanNameTextView.setText(clanName);
        mClanLevelTextView.setText("Clan Level: " + clan.getClanLevel());
        mClanWinRatioTextView.setText(String.format( "Win/Loss Ratio: %.2f", clan.getWinLossRatio()));

        clanTag = clan.getTag();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, ClanActivity.class);
        intent.putExtra("clanTag", clanTag);
        mContext.startActivity(intent);
//        final ArrayList<Clan> clans = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CLANS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    clans.add(snapshot.getValue(Clan.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, ClanActivity.class);
//                intent.putExtra("clanTag", clanTag);
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }
}
