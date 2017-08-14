package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {

    RecyclerView eventRecyclerView;
    EventListAdapter adapter;
    FloatingActionButton myFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);


        eventRecyclerView = (RecyclerView) findViewById(R.id.event_list);
        adapter = new EventListAdapter(this, getData());

        eventRecyclerView.setAdapter(adapter);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //launch create event activity here 
                Intent i = new Intent(EventList.this, CreateEvent.class);
                startActivity(i);
                finish();
            }
        });

    }

    private List<EventData> getData() {

        List<EventData> data = new ArrayList<>();


        //some fake data


        String[] titles = {"title1", "title2", "title3", "title4"};
        String[] Descriptions = {"this is event 1 ", "this is event 2 ", "this is event 3 ", "this is event 4"};
        String[] times = {"time1", "time2", "time3", "time4"};
        String[] places = {"place1", "place2", "place3", "place4"};


        for (int i = 0; i < titles.length; i++) {
            EventData current = new EventData();

            current.setTitle(titles[i]);
            current.setDescription(Descriptions[i]);
            current.setTime(times[i]);
            current.setPlace(places[i]);

            data.add(current);


        }
        return data;


    }
}
