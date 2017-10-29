package com.helpfully;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helpfully.work.Work;

import java.util.ArrayList;

/**
 * Created by Radoslaw on 2017-10-28.
 */

public class Database {
    //Firebase instance variables
    static private FirebaseDatabase mDatabase;
    static private DatabaseReference mDatabaseReference;
    static final private String USERS_DIR = "users";
    private static final String WORKS_DIR = "works";
    private static final String SHARE_DIR = "shares";

    public static String getWorksDir() {
        return WORKS_DIR;
    }
    public static String getShareDir() {
        return SHARE_DIR;
    }
    public static String getUserDir() {
        return USERS_DIR + "/" + getUserUID();
    }

    public static void initialize(boolean persistence) {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(persistence);
        }
    }

    static public DatabaseReference setLocation(String path) {
        DatabaseReference mDatabaseReference = mDatabase.getReference().child(path);
        return mDatabaseReference;
    }
    
    /**
     * Metoda publiczna pobierająca z bazy UID użytkownika
     * @return String UID lub null gdy użytkownik nie jest zalogowany
     */
    static public String getUserUID() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            return uid;
        }
        return null;
    }


    static public String[] getUserInfo(){
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

    public static void sendWorkToDatabase(Object work, User user) {
        initialize(true);
        DatabaseReference worksReference = setLocation(getWorksDir());
        DatabaseReference usersReference = setLocation(getUserDir());
        DatabaseReference newWorksReference = worksReference.push();
        String workID = newWorksReference.getKey();
        worksReference.child(workID).setValue(work);
        user.addWorks(workID);
        usersReference.setValue(user);
    }

    public static void sendShareToDatabase(Object share, User user) {
        initialize(true);
        DatabaseReference sharesReference = setLocation(getShareDir());
        DatabaseReference usersReference = setLocation(getUserDir());
        DatabaseReference newSharesReference = sharesReference.push();
        String shareID = newSharesReference.getKey();
        sharesReference.child(shareID).setValue(share);
        user.addShare(shareID);
        usersReference.setValue(user);
    }

//    static public void sendUserInfoToDatabase() {
//        initialize(true);
//        DatabaseReference users = setLocation(USERS_DIR);
//        users.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.child(getUserUID()).exists()) {
//                    // run some code
//                }else{
//                    String[] details = getUserInfo();
//                    User user = new User(details[0],details[1]);
//                    mDatabaseReference.child(getUserUID()).setValue(user);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        // mDatabaseReference.child(getUserUID()).child("pesel").setValue(pesel); - podmienianie tylko gałęzi pesel (można warunek if zrobić i sprawdzać czy pesel != null i dopiero wtedy podmieniać) to już zależy od metody zrobienia panelu do wprowadzania danych
//    }
}
