package com.joekramer.clashofclansplayerlook.models;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Clan {
    String tag;
    String name;
    String type;
    String description;
    //location object
    int locationId;
    String locationName;
    //badge image comes in small medium and large
    String badgeUrl;
    int clanLevel;
    int clanPoints;
    int clanVersusPoints;
    int requiredTrophies;
    int warWinStreak;
    int warWins;
    int warTies;
    int warLosses;
    int members;
    List<Member> memberList = new ArrayList<>();
    double winLossRatio;
    private String pushId;

    public Clan() {}

    public Clan(String tag, String name, String type, String description, int locationId,
                String locationName, String badgeUrl, int clanLevel, int clanPoints,
                int clanVersusPoints, int requiredTrophies, int warWinStreak,
                int warWins, int warTies, int warLosses, int members, ArrayList<Member> memberList) {
        this.tag = tag;
        this.name = name;
        this.type = type;
        this.description = description;
        this.locationId = locationId;
        this.locationName = locationName;
        this.badgeUrl = badgeUrl;
        this.clanLevel = clanLevel;
        this.clanPoints = clanPoints;
        this.clanVersusPoints = clanVersusPoints;
        this.requiredTrophies = requiredTrophies;
        this.warWinStreak = warWinStreak;
        this.warWins = warWins;
        this.warTies = warTies;
        this.warLosses = warLosses;
        this.members = members;
        this.memberList = memberList;
        this.winLossRatio = (double) warWins / warLosses;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public int getClanLevel() {
        return clanLevel;
    }

    public int getClanPoints() {
        return clanPoints;
    }

    public int getClanVersusPoints() {
        return clanVersusPoints;
    }

    public int getRequiredTrophies() {
        return requiredTrophies;
    }

    public int getWarWinStreak() {
        return warWinStreak;
    }

    public int getWarWins() {
        return warWins;
    }

    public int getWarTies() {
        return warTies;
    }

    public int getWarLosses() {
        return warLosses;
    }

    public int getMembers() {
        return members;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public double getWinLossRatio() { return winLossRatio; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    //TODO getLargeImageURL
}
