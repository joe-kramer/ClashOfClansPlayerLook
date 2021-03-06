package com.joekramer.clashofclansplayerlook.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.models.Member;
import com.joekramer.clashofclansplayerlook.ui.MemberDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Member> mMembers = new ArrayList<>();
    private Context mContext;

    public MemberListAdapter(Context context, ArrayList<Member> members) {
        mContext = context;
        mMembers = members;
    }

    @Override
    public MemberListAdapter.MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
        MemberViewHolder viewHolder = new MemberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MemberListAdapter.MemberViewHolder holder, int position) {
        holder.bindMember(mMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }




    //Viewholder class
    public class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.memberListImageView) ImageView mMemberListImageView;
        @Bind(R.id.memberListNameTextView) TextView mMemberListNameTextView;
        @Bind(R.id.memberListRoleTextView) TextView mMemberListRoleTextView;
        @Bind(R.id.memberListClanRankTextView) TextView mMemberListClanRankTextView;
        private Context mContext;

        public MemberViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindMember(Member member) {
            mMemberListNameTextView.setText(member.getName());
            mMemberListRoleTextView.setText(member.getRole());
            mMemberListClanRankTextView.setText("Clan Rank: " + member.getClanRank());
            Picasso.with(mContext)
                    .load(member.getLeagueIconUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mMemberListImageView);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MemberDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("members", Parcels.wrap(mMembers));
            mContext.startActivity(intent);
        }
    }
}
