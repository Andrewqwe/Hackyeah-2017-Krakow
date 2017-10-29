package com.helpfully.work;

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

import static com.helpfully.Database.getUserUID;

public class WorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<User> users = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        final EditText titleInput = findViewById(R.id.titleInput);
        final EditText descriptonInput = findViewById(R.id.descriptonInput);
        final EditText paymentInput = findViewById(R.id.paymentInput);
        Button sendBtn = findViewById(R.id.SendButton);
        Database.initialize(true);
        Database.setLocation("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(getUserUID()).exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }
//                if (dataSnapshot.getValue() != null) {
//                    User user = dataSnapshot.getValue(User.class);
//                    users.add(user);
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleInput.length() != 0 && descriptonInput.length() != 0 && paymentInput.length() != 0) {
                    System.out.println(titleInput.getText().toString());
                    System.out.println(descriptonInput.getText().toString());
                    System.out.println(Integer.parseInt(paymentInput.getText().toString()));
                    Work work = new Work(titleInput.getText().toString(), descriptonInput.getText().toString(), Integer.parseInt(paymentInput.getText().toString()));
                    System.out.println(work);
                    if (!users.isEmpty()) {
                        Database.sendWorkToDatabase(work, users.get(users.size()-1));
                    }
                    else {
                        Database.sendWorkToDatabase(work, new User(new ArrayList<String>()));
                    }
                }
            }
        });
    }
}
