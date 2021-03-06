package com.joekramer.clashofclansplayerlook.services;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.models.Clan;
import com.joekramer.clashofclansplayerlook.models.Member;
import com.joekramer.clashofclansplayerlookup.clientsdk.GetClanInfoAPIClient;
import com.joekramer.clashofclansplayerlookup.clientsdk.model.Empty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CocService  {
    public static final String TAG = CocService.class.getSimpleName();

    //    get clan info
    public static void findClan(String clanTag, Callback callback) {
        String encodedClanTag;
        try {
            encodedClanTag = URLEncoder.encode(clanTag, "UTF-8");
            Log.v(TAG, encodedClanTag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encodedClanTag = "INVALID";
        }

//        TODO add better error message for ip not valid
        OkHttpClient client = new OkHttpClient.Builder().build();

        //build url
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.COC_API_GATEWAY_CLANS).newBuilder();
        urlBuilder.addQueryParameter("clanInfo", encodedClanTag);
        String url = urlBuilder.build().toString();

        //Create new request
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    //puts api response from clan info api into models
    public static Clan processClanResults(Response response, String jsonData) {
        Clan clan = null;
        ArrayList<Member> memberList = new ArrayList<>();

        try {
//            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject clanJSON = new JSONObject(jsonData);

                //Grab Variables
                String tag = clanJSON.getString("tag");
                String name = clanJSON.getString("name");
                String type = clanJSON.getString("type");
                String description = clanJSON.getString("description");
                int locationId = clanJSON.getJSONObject("location").getInt("id");
                String locationName = clanJSON.getJSONObject("location").getString("name");
                String badgeUrl = clanJSON.getJSONObject("badgeUrls").getString("large");
                int clanLevel = clanJSON.getInt("clanLevel");
                int clanPoints = clanJSON.getInt("clanPoints");
                int clanVersusPoints = clanJSON.getInt("clanVersusPoints");
                int requiredTrophies = clanJSON.getInt("requiredTrophies");
                int warWinStreak = clanJSON.getInt("warWinStreak");
                int warWins = clanJSON.getInt("warWins");
                int warTies = clanJSON.getInt("warTies");
                int warLosses = clanJSON.getInt("warLosses");
                int members = clanJSON.getInt("members");
                JSONArray memberListJSON = clanJSON.getJSONArray("memberList");

                //populate memberList
                for(int i = 0; i < members; i++) {
                    JSONObject memberJSON = memberListJSON.getJSONObject(i);
                    String memberTag = memberJSON.getString("tag");
                    String memberName = memberJSON.getString("name");
                    String memberRole = memberJSON.getString("role");
                    int memberExpLevel = memberJSON.getInt("expLevel");
                    int leagueId = memberJSON.getJSONObject("league").getInt("id");
                    String leagueName = memberJSON.getJSONObject("league").getString("name");
                    String leagueIconUrl;
                    try {
                        leagueIconUrl = memberJSON.getJSONObject("league").getJSONObject("iconUrls").getString("medium");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Members medium league badge NA");
                        leagueIconUrl = memberJSON.getJSONObject("league").getJSONObject("iconUrls").getString("small");
                    }
                    int memberTrophies = memberJSON.getInt("trophies");
                    int memberVersusTrophies = memberJSON.getInt("versusTrophies");
                    int memberClanRank = memberJSON.getInt("clanRank");
                    int memberDonations = memberJSON.getInt("donations");
                    int memberDonationsReceived = memberJSON.getInt("donationsReceived");

                    Member newMember = new Member(memberTag, memberName, memberRole, memberExpLevel,
                            leagueId, leagueName, leagueIconUrl, memberTrophies, memberVersusTrophies,
                            memberClanRank, memberDonations, memberDonationsReceived);
                    memberList.add(newMember);
                }

                clan = new Clan(tag, name, type, description, locationId, locationName,
                        badgeUrl, clanLevel, clanPoints, clanVersusPoints, requiredTrophies,
                        warWinStreak, warWins, warTies, warLosses, members, memberList);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Return clan");
        return clan;
    }
}
