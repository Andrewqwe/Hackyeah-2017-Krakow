package com.helpfully;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.helpfully.work.WorkActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();  //Radek  //TODO: po wciśnięciu przycisku powrotu omijamy proces logowania naprawić!
        if (auth.getCurrentUser() != null) {
            // already signed in
            Toast.makeText(this,FirebaseAuth.getInstance().getCurrentUser().toString(),Toast.LENGTH_SHORT).show();
           // Database.sendUserInfoToDatabase(); //TODO: wrzucić dane do bazy danych
        } else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()

                                    ))
                            .build(),
                    RC_SIGN_IN);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButtonSettings = findViewById(R.id.imageButtonSettings);
        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSettings();
            }
        });

        ImageButton imageButtonWork = findViewById(R.id.imageButtonWork);
        imageButtonWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWork();
            }
        });

        ImageButton imageButtonMyAdverts = findViewById(R.id.imageButtonMy);
        imageButtonMyAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartMyAdverts();
            }
        });
    }

    private void startSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void startWork() {
        startActivity(new Intent(this, WorkActivity.class));
    }

    private void StartMyAdverts(){
        startActivity(new Intent(this, MyAdvertsActivity.class));
    }
}
