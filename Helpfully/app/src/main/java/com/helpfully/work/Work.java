package com.helpfully.work;

import com.helpfully.Database;

/**
 * Created by Damian on 28.10.2017.
 */

public class Work {
    private String title;
    private String descripton;
    private int payment;
    private String userID;

    public Work(String title, String descripton, int payment) {
        this.title = title;
        this.descripton = descripton;
        this.payment = payment;
        userID = Database.getUserUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
