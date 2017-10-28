package com.helpfully;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();  //Radek  //TODO: po wciśnięciu przycisku powrotu omijamy proces logowania naprawić!
        if (auth.getCurrentUser() != null) {
            // already signed in
            Toast.makeText(this,FirebaseAuth.getInstance().getCurrentUser().toString(),Toast.LENGTH_SHORT).show();
           // Database.SendUserInfoToDatabase(); //TODO: wrzucić dane do bazy danych
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
    }
}
