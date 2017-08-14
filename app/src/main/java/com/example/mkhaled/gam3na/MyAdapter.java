package com.example.mkhaled.gam3na;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkhaled on 30/07/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<ItemData> itemDataList = Collections.emptyList();
    Context con;
    private LayoutInflater inflater;


    public MyAdapter(Context context, List<ItemData> itemDataList) {
        inflater = LayoutInflater.from(context);
        this.itemDataList = itemDataList;
        con = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ItemData itemData = itemDataList.get(position);
        holder.itemImage.setImageResource(itemData.getImgId());
        holder.itemTitle.setText(itemData.getTitle());


    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemImage;
        TextView itemTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Intent i = new Intent(view.getContext(), EventList.class);
            i.putExtra("selectedcommunity", position);
            view.getContext().startActivity(i);

        }
    }
}
