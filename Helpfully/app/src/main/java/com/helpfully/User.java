package com.helpfully;

import java.util.ArrayList;

/**
 * Created by Radoslaw on 2017-10-28.
 */

public class User {
    private ArrayList<String> works = new ArrayList<>();

    public User(ArrayList<String> works) {
        this.works = works;
    }

    public User() {
    }

    public ArrayList<String> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<String> works) {
        this.works = works;
    }

    public void addWorks(String workID) {
        works.add(workID);
    }
}

