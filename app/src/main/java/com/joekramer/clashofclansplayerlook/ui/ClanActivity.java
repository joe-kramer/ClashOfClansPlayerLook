package com.joekramer.clashofclansplayerlook.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joekramer.clashofclansplayerlook.Constants;
import com.joekramer.clashofclansplayerlook.adapters.MemberListAdapter;
import com.joekramer.clashofclansplayerlook.models.Clan;
import com.joekramer.clashofclansplayerlook.models.Member;
import com.joekramer.clashofclansplayerlook.services.CocService;
import com.joekramer.clashofclansplayerlook.R;
import com.joekramer.clashofclansplayerlookup.clientsdk.GetClanInfoAPIClient;
import com.joekramer.clashofclansplayerlookup.clientsdk.model.Empty;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.parceler.Parcels;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClanActivity extends AppCompatActivity {
    public static final String TAG = ClanActivity.class.getSimpleName();
    public Clan mClan = null;
    @Bind(R.id.getMembersButton) Button mGetMembersButton;
    @Bind(R.id.clanNameTextView) TextView mClanNameTextView;
    @Bind(R.id.clanLevelTextView) TextView mClanLevelTextView;
    @Bind(R.id.clanDescriptionTextView) TextView mClanDescriptionTextView;
    @Bind(R.id.clanBadgeImageView) ImageView mClanBadgeImageView;
    @Bind(R.id.clanTagTextView) TextView mClanTagTextView;
    @Bind(R.id.clanTypeTextView) TextView mClanTypeTextView;
    @Bind(R.id.clanLocationTextView) TextView mClanLocationTextView;
    @Bind(R.id.clanRequiredTrophiesTextView) TextView mClanRequiredTrophiesTextView;
    @Bind(R.id.warStreakTextView) TextView mWarStreakTextView;
    @Bind(R.id.clanPointsTextView) TextView mClanPointsTextView;
    @Bind(R.id.clanVersusPointsTextView) TextView mClanVersusPointsTextView;
    @Bind(R.id.warWinsTextView) TextView mWarWinsTextView;
    @Bind(R.id.warTiesTextView) TextView mWarTiesTextView;
    @Bind(R.id.warLossesTextView) TextView mWarLossesTextView;
    @Bind(R.id.warWinPercentageTextView) TextView mWarWinPercentageTextView;

    //Progress dialog
    private ProgressDialog mAuthProgressDialog;

    //background
//    @Bind(R.id.picLinearLayout) LinearLayout mPicLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);
        ButterKnife.bind(this);

        //get clanTag
        Intent intent = getIntent();
        String clanTag = intent.getStringExtra("clanTag");

        //set clan
        getClanInfo(clanTag);

        //progress dialog for api call to Lambda
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Fetching Clan info");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();
    }

    //TODO: Move to Main for Progress Dialog and Search And other things
    private void getClanInfo(String clanTag) {
        final CocService cocService = new CocService();

        cocService.findClan(clanTag, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mAuthProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mAuthProgressDialog.dismiss();

                try {
                    //Json string coming back with slashes, need to reformat
                    String jsonData = response.body().string();
                    jsonData = jsonData.trim();
                    jsonData = jsonData.substring(1, jsonData.length() - 1);
                    jsonData = jsonData.replace("\\","");
                        Log.v(TAG, jsonData);
                        //TODO could just put jsonData as response
                        mClan = CocService.processClanResults(response, jsonData);

                        //put response back onto main thread
                        ClanActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //CLAN INFO
                                mClanNameTextView.setText(mClan.getName());
                                mClanLevelTextView.setText("Clan Level: " + mClan.getClanLevel());
                                //TODO put font on all/ use coc font
                                //font
                                Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
                                mClanNameTextView.setTypeface(titleFont);
                                //TODO make description text area size adjustable. or click to see rest
                                mClanDescriptionTextView.setText(mClan.getDescription());
                                //TODO center badge and make width based off height
//                                //clan badge pic
                                Picasso.with(getApplicationContext()).load(mClan.getBadgeUrl())
                                .into(mClanBadgeImageView);
                                mClanTagTextView.setText("Tag: " + mClan.getTag());
                                mClanTypeTextView.setText("Type: " + mClan.getType());
                                mClanRequiredTrophiesTextView.setText("Required Trophies: " + mClan.getRequiredTrophies());
                                mClanLocationTextView.setText(mClan.getLocationName());
                                mClanPointsTextView.setText("Clan Points: " + mClan.getClanPoints());
                                mClanVersusPointsTextView.setText("Clan Versus Points: " + mClan.getClanVersusPoints());
                                mWarStreakTextView.setText("Win Streak: " + mClan.getWarWinStreak());
                                mWarWinsTextView.setText("War Wins: " + mClan.getWarWins());
                                mWarTiesTextView.setText("War Ties: " + mClan.getWarTies());
                                mWarLossesTextView.setText("War Losses: " + mClan.getWarLosses());
                                DecimalFormat formatter = new DecimalFormat("#0.00");
                                mWarWinPercentageTextView.setText("Win/Loss Ratio: " + formatter.format(mClan.getWinLossRatio()));

                                //set button
                                mGetMembersButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //TODO push only memberlist array, not whole clan
                                        List<Member> memberList1 = mClan.getMemberList();
                                        ArrayList<Member> memberList = (ArrayList<Member>) memberList1;

                                        //send to player activity
                                        Intent intent = new Intent(ClanActivity.this, MemberListActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelableArrayList("members", memberList);
                                        intent.putExtra("membersBundle", bundle);
                                        startActivity(intent);
                                    }
                                });
                                mGetMembersButton.setText("View clan's " + mClan.getMembers() + " members");

                                //background on linear clan_list_item
//                                new LoadBackground(mClan.mBadgeUrl, "clanBackground").execute();
                            }
                        });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_clan, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                getClanInfo(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_saveClan) {
            //get user id for firebase save
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            //get user's node and set to reference
            DatabaseReference clansRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CLANS)
                    .child(uid);

            //push saved clan into user's node under own push Id
            DatabaseReference pushRef = clansRef.push();
            String pushId = pushRef.getKey();
            mClan.setPushId(pushId);
            pushRef.setValue(mClan);

            Toast.makeText(ClanActivity.this, "Clan Saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private class LoadBackground extends AsyncTask<String, Void, Drawable> {
//
//        private String imageUrl, imageName;
//
//        public LoadBackground(String url, String file_name) {
//            this.imageUrl = url;
//            this.imageName = file_name;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Drawable doInBackground(String... urls) {
//
//            try {
//                InputStream is = (InputStream) this.fetch(this.imageUrl);
//                Drawable d = Drawable.createFromStream(is, this.imageName);
//                return d;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        private Object fetch(String address) throws MalformedURLException, IOException {
//            URL url = new URL(address);
//            Object content = url.getContent();
//            return content;
//        }
//
//        @Override
//        protected void onPostExecute(Drawable result) {
//            super.onPostExecute(result);
//            mPicLinearLayout.setBackground(result);
//        }
//    }
}
