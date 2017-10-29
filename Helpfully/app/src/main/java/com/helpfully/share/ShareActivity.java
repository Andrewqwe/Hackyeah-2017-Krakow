package com.helpfully.share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.helpfully.Database;
import com.helpfully.R;
import com.helpfully.User;

import java.util.ArrayList;

import static com.helpfully.Database.getUserDir;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<User> users = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shares);
        final EditText titleInput = findViewById(R.id.titleInput);
        final EditText descriptonInput = findViewById(R.id.descriptonInput);
        Button sendBtn = findViewById(R.id.SendButton);
        Database.initialize(true);
        Database.setLocation(getUserDir()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleInput.length() != 0 && descriptonInput.length() != 0) {
                    System.out.println(titleInput.getText().toString());
                    System.out.println(descriptonInput.getText().toString());
                    Share share = new Share(titleInput.getText().toString(), descriptonInput.getText().toString(), 0);
                    System.out.println(share);
                    Database.sendShareToDatabase(share, users.get(users.size()-1));
                }
            }
        });
    }
}
