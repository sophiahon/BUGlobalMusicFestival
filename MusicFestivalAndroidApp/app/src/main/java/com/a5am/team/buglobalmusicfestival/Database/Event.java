package com.a5am.team.buglobalmusicfestival.Database;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pro on 2018/3/28.
 *
 * Serializable to make it able to put into bundle, or pass by intent.
 */

public class Event implements Serializable {

    public int id;
    public String event;
    public String artist;
    public String place;
    public String date;


    public Event() {}

    public Event(int id, String event, String artist, String place, String date) {
        this.id = id;
        this.event = event;
        this.artist = artist;
        this.place = place;
        this.date = date;
    }


}
