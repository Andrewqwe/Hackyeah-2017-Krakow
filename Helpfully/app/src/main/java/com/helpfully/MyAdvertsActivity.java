package com.helpfully;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.helpfully.work.Advertable;
import com.helpfully.work.Work;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdvertsActivity extends AppCompatActivity {

    AdvertsListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adverts);
        final ArrayList<Advertable> arr = new ArrayList<>();
        adapter = new AdvertsListAdapter(arr, this);
        listView = findViewById(R.id.advertsListView);
        listView.setAdapter(adapter);
        final ArrayList<HashMap<String, Work>> worksy = new ArrayList<>();
        final ArrayList<User> users = new ArrayList<>();
        Database.initialize(true);
        Database.setLocation(Database.getUserDir()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);

                    for (String workID : users.get(0).getWorks()) {
                        Database.setLocation(Database.getWorksDir()).child(workID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                System.out.println(dataSnapshot.getValue(Work.class));
                                arr.add(dataSnapshot.getValue(Work.class));
                                adapter.notifyDataSetChanged();
                /*worksy.add((HashMap<String, Object>) dataSnapshot.getValue());
                for(HashMap<String, Work> hashMap : worksy){
                    System.out.println(hashMap);
                    for(Work w: hashMap.values()){
                        if(w.getUserID().equals(Database.getUserUID())){
                            arr.add(w);
                        }
                    }
                }*/
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

