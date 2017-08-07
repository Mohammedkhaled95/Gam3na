package com.example.mkhaled.gam3na;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        adapter=new MyAdapter(this,getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,1));



    }


    List<ItemData> getData(){
        List<ItemData> data=new ArrayList<>();
        int img_id[]={R.drawable.bicycle,
                R.drawable.blood,
                R.drawable.book,
                R.drawable.painting,
                R.drawable.runner,
                R.drawable.socialvisits,
                R.drawable.add
             };
        String titles[]={"Bicycling","Blood Donation","Reading Club","Painting","Runners","Charity","add new community"};


        for (int i=0;i<titles.length&&i<img_id.length;i++)
        {
            ItemData current=new ItemData();

            current.setImgId(img_id[i]);
            current.setTitle(titles[i]);
            data.add(current);


        }

        return data;


    }
}
