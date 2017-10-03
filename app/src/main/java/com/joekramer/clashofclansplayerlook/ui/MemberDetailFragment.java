package com.joekramer.clashofclansplayerlook.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlook.models.Member;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 400;
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    //TODO: Make sure you can tell an interviewer the difference between Serializable and Parcelable.
    @Bind(R.id.memberFragmentImageView) ImageView mMemberFragmentImageView;
    @Bind(R.id.memberFragmentNameTextView) TextView mMemberFragmentNameTextView;
    @Bind(R.id.memberFragmentRoleTextView) TextView mMemberFragmentRoleTextView;
    @Bind(R.id.memberFragmentExpLevelTextView) TextView mMemberFragmentExpLevelTextView;
    @Bind(R.id.memberFragmentTrophiesTextView) TextView mMemberFragmentTrophiesTextView;
    @Bind(R.id.memberFragmentVersusTrophiesTextView) TextView mMemberFragmentVersusTrophiesTextView;
    @Bind(R.id.memberFragmentClanRankTextView) TextView mMemberFragmentClanRankTextView;
    @Bind(R.id.memberFragmentDonationsTextView) TextView mMemberFragmentDonationsTextView;
    @Bind(R.id.memberFragmentDonationsRecievedTextView) TextView mMemberFragmentDonationsRecievedTextView;
    @Bind(R.id.memberFragmentLeagueNameTextView) TextView mMemberFragmentLeagueNameTextView;
    @Bind(R.id.memberFragmentLeagueIdTextView) TextView mMemberFragmentLeagueIdTextView;

    private Member mMember;

    public static MemberDetailFragment newInstance(Member member) {
        MemberDetailFragment memberDetailFragment = new MemberDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("member", Parcels.wrap(member));
        memberDetailFragment.setArguments(args);
        return memberDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMember = Parcels.unwrap(getArguments().getParcelable("member"));
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member_detail, container, false);
        ButterKnife.bind(this, view);

        mMemberFragmentNameTextView.setText(mMember.getName());
        mMemberFragmentExpLevelTextView.setText("Exp Level: " + mMember.getExpLevel());
        mMemberFragmentRoleTextView.setText(mMember.getRole());
        mMemberFragmentTrophiesTextView.setText("Total Trophies: " + mMember.getTrophies());
        mMemberFragmentVersusTrophiesTextView.setText("Total Versus Trophies: " + mMember.getVersusTrophies());
        mMemberFragmentClanRankTextView.setText("Clan Rank: " + mMember.getClanRank());
        mMemberFragmentDonationsTextView.setText("Donations: " + mMember.getDonations());
        mMemberFragmentDonationsRecievedTextView.setText("Donations Recieved: " + mMember.getDonationsReceived());
        mMemberFragmentLeagueNameTextView.setText("League: " + mMember.getLeagueName());
        mMemberFragmentLeagueIdTextView.setText("League Id: " + mMember.getLeagueId());

        Picasso.with(view.getContext())
                .load(mMember.getLeagueIconUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mMemberFragmentImageView);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        if (mSource.equals(Constants.SOURCE_SAVED)) {
        inflater.inflate(R.menu.menu_photo, menu);
//        } else {
//            inflater.inflate(R.menu.menu_main, menu);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mMemberFragmentImageView.setImageBitmap(imageBitmap);
//            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

//    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference(Constants.FIREBASE_CHILD_CLANS)
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child(Constants.PHOTO);
//        ref.setValue(imageEncoded);
//    }

}
