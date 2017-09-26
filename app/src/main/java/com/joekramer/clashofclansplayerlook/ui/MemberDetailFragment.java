package com.joekramer.clashofclansplayerlook.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.models.Member;

import org.parceler.Parcels;

import butterknife.Bind;

public class MemberDetailFragment extends Fragment {
    //TODO: Make sure you can tell an interviewer the difference between Serializable and Parcelable.
    @Bind(R.id.memberFragmentImageView) ImageView mMemberFragmentImageView;

    private Member mMember;

    public static MemberDetailFragment newInstance(Member member) {
        MemberDetailFragment memberDetailFragment = new MemberDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("member", Parcels.wrap(member));
        memberDetailFragment.setArguments(args);
        return memberDetailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_detail, container, false);
    }

}
