package com.example.android.androidchallenge;

/**
 * Created by Pengqi on 2/22/2018.
 */

public class Guide {
    private String eventName;
    private String eventLocation;
    private String eventDate;
    private String eventImageURL;

    public Guide(String name, String location, String date, String imageURL){
        this.eventName = name;
        this.eventLocation = location;
        this.eventDate = date;
        this.eventImageURL = imageURL;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }
}
