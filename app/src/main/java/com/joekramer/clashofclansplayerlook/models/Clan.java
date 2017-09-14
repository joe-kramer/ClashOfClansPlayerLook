package com.joekramer.clashofclansplayerlook.models;


public class Clan {
    private String mTag;
    private String mName;
    private String mDescription;
    //location object
    private String mLocation;
    //badge image comes in small medium and large
    private String mBadgeUrls;
    private int mClanLevel;
    private int mClanPoints;
    private int mClanVersusPoints;
    private int mRequiredTrophies;
    private int mWarWinStreak;
    private int mWarWins;
    private int mWarTies;
    private int mWarLosses;
    private int mMembers;
    //memberList object
    private Member[] mMemberList;

    public Clan() {

    }
}
