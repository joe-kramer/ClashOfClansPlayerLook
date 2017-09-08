package com.joekramer.clashofclansplayerlook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClanMembersListActivity extends AppCompatActivity {
    @Bind(R.id.clanMembersListView) ListView mClanMembersListView;
    private String[] members = new String[] {
            "Ryan Moloney",
            "Sam Kramer",
            "Kyle Miyahara",
            "Michael Parrot",
            "Adam Johnson",
            "Bryan Langdal",
            "Joe Kramer"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, members);
        mClanMembersListView.setAdapter(adapter);

        mClanMembersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String member = ((TextView) view).getText().toString();
                Toast.makeText(ClanMembersListActivity.this, member, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
