
package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.*;
import com.backendless.persistence.QueryOptions;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class EventList extends AppCompatActivity {


    RecyclerView eventRecyclerView;
    EventListAdapter adapter;
    FloatingActionButton myFab;
    int community;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    List<Map> retrievedMap = new ArrayList<>();
    List<EventData> eventDataList = new ArrayList<>();



   /* String name;
    String email;

    TextView tvname;
    TextView tvemail;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);

       /* name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");

        tvname=(TextView) findViewById(R.id.username);
        tvemail=(TextView) findViewById(R.id.useremail);

        tvemail.setText(email);
        tvname.setText(name);*/


        community = getIntent().getIntExtra("selectedcommunity", -1);

        eventRecyclerView = (RecyclerView) findViewById(R.id.event_list);
        retrieveData(String.valueOf(community));
        myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //launch create event activity here 
                Intent i = new Intent(EventList.this, CreateEvent.class);
                //send a the community name as a extra to detect which community to deal with
                i.putExtra("selectedcommunity", community);
                startActivity(i);
                finish();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    void retrieveData(String community) {  //List<Map>

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);

        String whereClause = "community = " + community;
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("EVENT").find(queryBuilder,
                new AsyncCallback<List<Map>>() {
                    @Override
                    public void handleResponse(List<Map> foundContacts) {
                        // every loaded object from the "EVENT" table is now an individual java.util.Map
                        List<EventData> eventlist = new ArrayList<>();
                        if (foundContacts.size() > 0) {
                            for (int i = 0; i < foundContacts.size(); i++) {

                                Map m = foundContacts.get(i);
                                EventData data = new EventData();
                                data.setTitle(m.get("title").toString());
                                data.setDescription(m.get("description").toString());
                                data.setPlace(m.get("place").toString());
                                data.setTime(m.get("time").toString());
                                retrievedMap.add(m);
                                eventlist.add(data);
                                adapter = new EventListAdapter(getApplication(), eventlist);

                                eventRecyclerView.setAdapter(adapter);

                                eventRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                            }
                            Log.e("Returned List SIZE", String.valueOf(retrievedMap.size()));
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.e("ERROR", fault.getMessage());
                    }
                });
        Log.e("2222222 Returned SIZE", String.valueOf(retrievedMap.size()));
    }


    List<EventData> fillEventList() {//List<Map> lmap
        //  List<Map> lmap = retrieveData(String.valueOf(community));


        if (retrievedMap.size() > 0) {
            for (int i = 0; i < retrievedMap.size(); i++) {
                EventData current = new EventData();
                current.setTitle(retrievedMap.get(i).get("title").toString());
                current.setTime(retrievedMap.get(i).get("time").toString());
                current.setPlace(retrievedMap.get(i).get("place").toString());
                current.setDescription(retrievedMap.get(i).get("description").toString());


                eventDataList.add(current);
                Log.e("EVENTDATALIST Details", eventDataList.get(0).getTitle());
                Log.e("EVENTDATALIST size ", String.valueOf(eventDataList.size()));
            }
        } else {
            Log.e("Empty", "EMPTY ????");
        }


        return (eventDataList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        if (mToggle.onOptionsItemSelected(item)) {

            switch (item.getItemId()) {
                case R.id.nav_app:
                    //launch
                    startActivity(new Intent("com.example.mkhaled.gam3na.MainActivity"));
                    finish();

                    return true;
                case R.id.nav_setting:
                    Toast.makeText(this, "Not Supported Yet :(", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
