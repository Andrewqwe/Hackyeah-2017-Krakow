package com.helpfully;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Radoslaw on 2017-10-28.
 */

public class Database {
    //Firebase instance variables
    static private FirebaseDatabase mDatabase;
    static private DatabaseReference mDatabaseReference;
    static final private String users_dir = "users";

    public static void Initialize(boolean persistence) {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(persistence);
        }
    }

    static public DatabaseReference SetLocation(String path) {
        DatabaseReference mDatabaseReference = mDatabase.getReference().child(path);
        return mDatabaseReference;
    }
    
    /**
     * Metoda publiczna pobierająca z bazy UID użytkownika
     * @return String UID lub null gdy użytkownik nie jest zalogowany
     */
    static public String GetUserUID() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            return uid;
        }
        return null;
    }


    static public String[]  GetUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address
            String name = user.getDisplayName();
            String email = user.getEmail();
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            String[] details = {name,email,uid};
            return details;
        }
        return null;
    }


    static public void SendUserInfoToDatabase() {
        Initialize(true);
        DatabaseReference users = SetLocation(users_dir);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(GetUserUID()).exists()) {
                    // run some code
                }else{
                    String[] details = GetUserInfo();
                    User user = new User(details[0],details[1]);
                    mDatabaseReference.child(GetUserUID()).setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // mDatabaseReference.child(GetUserUID()).child("pesel").setValue(pesel); - podmienianie tylko gałęzi pesel (można warunek if zrobić i sprawdzać czy pesel != null i dopiero wtedy podmieniać) to już zależy od metody zrobienia panelu do wprowadzania danych
    }
}
