package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        adapter=new MyAdapter(this,getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,1));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                    Toast.makeText(getApplicationContext(), "Not Supported Yet :(", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
    }



    List<ItemData> getData(){
        List<ItemData> data=new ArrayList<>();
        int img_id[]={R.drawable.bicycle,
                R.drawable.blood,
                R.drawable.book,
                R.drawable.painting,
                R.drawable.runner,
                R.drawable.socialvisits,
             };
        String titles[] = {"Bicycling", "Blood Donation", "Reading Club", "Painting", "Runners", "Charity"};


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
