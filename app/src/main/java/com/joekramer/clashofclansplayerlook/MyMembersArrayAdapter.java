package com.joekramer.clashofclansplayerlook;


import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;


public class MyMembersArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mMembers;

    public MyMembersArrayAdapter(Context mContext, int resource, String[] members) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMembers = members;
    }

    @Override
    public int getCount() {
        return mMembers.length;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        String member = mMembers[position];
        return String.format("%d) Name: %s", position + 1, member);
    }
}
