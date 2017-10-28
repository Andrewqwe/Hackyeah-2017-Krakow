package com.helpfully;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Radoslaw on 2017-10-28.
 */

public class Database {
    //Firebase instance variables
    static private FirebaseDatabase mDatabase;


    public static void initialize(boolean persistence) {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(persistence);
        }
    }

}
