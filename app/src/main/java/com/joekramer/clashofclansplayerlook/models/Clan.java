package com.joekramer.clashofclansplayerlook.models;


import java.util.ArrayList;

public class Clan {
    public String mTag;
    public String mName;
    public String mType;
    public String mDescription;
    //location object
    public int mLocationId;
    public String mLocationName;
    //badge image comes in small medium and large
    public String mBadgeUrl;
    public int mClanLevel;
    public int mClanPoints;
    public int mClanVersusPoints;
    public int mRequiredTrophies;
    public int mWarWinStreak;
    public int mWarWins;
    public int mWarTies;
    public int mWarLosses;
    public int mMembers;
    //memberList object
    public ArrayList<Member> mMemberList;

    public Clan(String tag, String name, String type, String description, int locationId,
                String locationName, String badgeUrl, int clanLevel, int clanPoints,
                int clanVersusPoints, int requiredTrophies, int warWinStreak,
                int warWins, int warTies, int warLosses, int members, ArrayList<Member> memberList) {
        this.mTag = tag;
        this.mName = name;
        this.mType = type;
        this.mDescription = description;
        this.mLocationId = locationId;
        this.mLocationName = locationName;
        this.mBadgeUrl = badgeUrl;
        this.mClanLevel = clanLevel;
        this.mClanPoints = clanPoints;
        this.mClanVersusPoints = clanVersusPoints;
        this.mRequiredTrophies = requiredTrophies;
        this.mWarWinStreak = warWinStreak;
        this.mWarWins = warWins;
        this.mWarTies = warTies;
        this.mWarLosses = warLosses;
        this.mMembers = members;
        this.mMemberList = memberList;
    }
}
