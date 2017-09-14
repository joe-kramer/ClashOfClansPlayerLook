package com.joekramer.clashofclansplayerlook.models;


public class Member {
    private String mTag;
    private String mName;
    private String mRole;
    private int mExpLevel;
    //from league object
    private int mLeagueId;
    private String mLeagueName;
    private String mLeagueIconUrl;

    private int mTrophies;
    private int mVersusTrophies;
    private int mClanRank;
    private int mDonations;
    private int mDonationsReceived;

    public Member(String tag, String name, String role, int expLevel, int leagueId,
                  String leagueName, String leagueIconUrl, int trophies, int versusTrophies,
                  int clanRank, int donations, int donationsReceived) {
        this.mTag = tag;
        this.mName = name;
        this.mRole = role;
        this.mExpLevel = expLevel;
        this.mLeagueId = leagueId;
        this.mLeagueName = leagueName;
        this.mLeagueIconUrl = leagueIconUrl;
        this.mTrophies = trophies;
        this.mVersusTrophies = versusTrophies;
        this.mClanRank = clanRank;
        this.mDonations = donations;
        this.mDonationsReceived = donationsReceived;
    }

    public String getTag() {
        return mTag;
    }

    public String getName() {
        return mName;
    }

    public String getRole() {
        return mRole;
    }

    public int getExpLevel() {
        return mExpLevel;
    }

    public int getLeagueId() {
        return mLeagueId;
    }

    public String getLeagueName() {
        return mLeagueName;
    }

    public String getLeagueIconUrl() {
        return mLeagueIconUrl;
    }

    public int getTrophies() {
        return mTrophies;
    }

    public int getVersusTrophies() {
        return mVersusTrophies;
    }

    public int getClanRank() {
        return mClanRank;
    }

    public int getDonations() {
        return mDonations;
    }

    public int getDonationsReceived() {
        return mDonationsReceived;
    }
}
