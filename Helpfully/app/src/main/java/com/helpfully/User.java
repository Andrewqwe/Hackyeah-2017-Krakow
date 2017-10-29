package com.helpfully;

import java.util.ArrayList;

/**
 * Created by Radoslaw on 2017-10-28.
 */

public class User {
    private ArrayList<String> works;
    private ArrayList<String> shares;

    public User() {
        works = new ArrayList<>();
        shares = new ArrayList<>();
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

    public ArrayList<String> getShares() {
        return shares;
    }

    public void setShares(ArrayList<String> shares) {
        this.shares = shares;
    }

    public void addShare(String shareID) {
        shares.add(shareID);
    }
}

