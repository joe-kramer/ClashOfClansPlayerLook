package com.joekramer.clashofclansplayerlook.services;

import android.util.Log;

import com.joekramer.clashofclansplayerlook.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CocService {
    public static final String TAG = CocService.class.getSimpleName();

    public static void findPlayer(String playerTag, Callback callback) {

        //factory for calls, which can be used to send http requests and read their responses
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.COC_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.COC_MY_PLAYER_TAG, player);
        String url = urlBuilder.build().toString().concat(Constants.COC_MY_PLAYER_TAG);
        try {
            String encodedClanTag = URLEncoder.encode(Constants.COC_MY_CLAN_TAG, "UTF-8");
            Log.v(TAG, encodedClanTag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Create new request
        Request request = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + Constants.COC_TOKEN_TAYLORS_HOUSE).build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
