package com.joekramer.clashofclansplayerlook.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joekramer.clashofclansplayerlook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberDetailFragment extends Fragment {


    public MemberDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_detail, container, false);
    }

}
