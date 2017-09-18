package com.joekramer.clashofclansplayerlook.models;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Member implements Parcelable {
    public String mTag;
    public String mName;
    public String mRole;
    public int mExpLevel;
    //from league object
    public int mLeagueId;
    public String mLeagueName;
    public String mLeagueIconUrl;

    public int mTrophies;
    public int mVersusTrophies;
    public int mClanRank;
    public int mDonations;
    public int mDonationsReceived;

    // empty constructor needed by the Parceler library
    public Member() {}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.mTag);
        dest.writeString(this.mName);
        dest.writeString(this.mRole);
        dest.writeInt(this.mExpLevel);
        dest.writeInt(this.mLeagueId);
        dest.writeString(this.mLeagueName);
        dest.writeString(this.mLeagueIconUrl);
        dest.writeInt(this.mTrophies);
        dest.writeInt(this.mVersusTrophies);
        dest.writeInt(this.mClanRank);
        dest.writeInt(this.mDonations);
        dest.writeInt(this.mDonationsReceived);
    }

    protected Member(android.os.Parcel in) {
        this.mTag = in.readString();
        this.mName = in.readString();
        this.mRole = in.readString();
        this.mExpLevel = in.readInt();
        this.mLeagueId = in.readInt();
        this.mLeagueName = in.readString();
        this.mLeagueIconUrl = in.readString();
        this.mTrophies = in.readInt();
        this.mVersusTrophies = in.readInt();
        this.mClanRank = in.readInt();
        this.mDonations = in.readInt();
        this.mDonationsReceived = in.readInt();
    }

    public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>() {
        @Override
        public Member createFromParcel(android.os.Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
