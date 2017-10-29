package com.helpfully;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ScrollView;

import com.helpfully.work.Advertable;
import com.helpfully.work.Work;

import java.util.ArrayList;

public class MyAdvertsActivity extends AppCompatActivity {

    AdvertsListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adverts);
        adapter = new AdvertsListAdapter(this,R.layout.advertitem);
        listView = findViewById(R.id.advertsListView);
        listView.setAdapter(adapter);
        Work w =new Work("cokolwiek","opis",50);
        Work w2 =new Work("cokolwiek2","opis2",80);
        adapter.add(w);
        adapter.add(w2);
        adapter.notifyDataSetChanged();
    }
}
