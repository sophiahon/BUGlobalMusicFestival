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
    public int aid;
    public String place;
    public String date;
    public String artist;


    public Event() {}

    public Event(int id, String event, int aid, String place, String date, String artist) {
        this.id = id;
        this.event = event;
        this.aid = aid;
        this.place = place;
        this.date = date;
        this.artist = artist;
    }


}
