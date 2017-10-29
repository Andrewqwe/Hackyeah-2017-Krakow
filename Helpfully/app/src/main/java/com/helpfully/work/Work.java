package com.helpfully.work;

import com.helpfully.Database;

import java.util.Date;

/**
 * Created by Damian on 28.10.2017.
 */

public class Work {
    private String title;
    private String description;
    private int payment;
    private String userID;
    private double latitude;
    private double longitude;
    private String URL;
    private int counter;
    private Date date;


    public Work(String title, String description, int payment) {
        this.title = title;
        this.description = description;
        this.payment = payment;
        userID = Database.getUserUID();
    }

    public Work(String title, String description, int payment, double latitude, double longitude, String URL, int counter, Date date) {
        this.title = title;
        this.description = description;
        this.payment = payment;
        userID = Database.getUserUID();
        this.latitude = latitude;
        this.longitude = longitude;
        this.URL = URL;
        this.counter = counter;
        this.date = date;
    }

    public Work(String title, String description, int payment, String URL) {
        this.title = title;
        this.description = description;
        this.payment = payment;
        userID = Database.getUserUID();
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
