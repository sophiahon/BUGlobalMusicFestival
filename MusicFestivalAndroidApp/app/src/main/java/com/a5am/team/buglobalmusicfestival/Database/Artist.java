package com.a5am.team.buglobalmusicfestival.Database;

import android.media.Image;

/**
 * Created by pro on 2018/3/31.
 */

public class Artist {


    public int aid;
    public String artist;
    public String website;
    public String country;
    public String spotify;

    public Artist() {}

    public Artist(int aid, String artist, String website, String country, String spotify) {
        this.aid = aid;
        this.artist = artist;
        this.website = website;
        this.country = country;
        this.spotify = spotify;
    }

}
