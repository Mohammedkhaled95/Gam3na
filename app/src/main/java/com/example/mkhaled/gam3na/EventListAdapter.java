package com.example.mkhaled.gam3na;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkhaled on 10/08/17.
 */

public class EventListAdapter extends RecyclerView.Adapter <EventListAdapter.EventViewHolder>{

    private LayoutInflater inflater;
    List<EventData> eventDataList = Collections.emptyList();
    Context con;

    public EventListAdapter( Context con,List<EventData> itemDataList) {
        this.inflater = LayoutInflater.from(con);
        this.eventDataList = itemDataList;
        this.con = con;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.event_item, parent, false);
        EventViewHolder holder = new EventViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventData eventData = eventDataList.get(position);
        holder.eventTitle.setText(eventData.getTitle());
        holder.eventDescrition.setText(eventData.getDescription());
        holder.eventTime.setText(eventData.getTime());
        holder.eventPlace.setText(eventData.getPlace());

    }



    @Override
    public int getItemCount() {
        return eventDataList.size();

    }

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventTitle;
        TextView eventDescrition;
        TextView eventTime;
        TextView eventPlace;


        public EventViewHolder(View itemView) {
            super(itemView);
            eventTitle = (TextView) itemView.findViewById(R.id.event_title);
            eventDescrition = (TextView) itemView.findViewById(R.id.event_description);
            eventTime = (TextView) itemView.findViewById(R.id.event_time);
            eventPlace = (TextView) itemView.findViewById(R.id.event_place);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
          /*  Intent i = new Intent(view.getContext(), //displayActivity);
            view.getContext().startActivity(i);*/

        }
    }
}
