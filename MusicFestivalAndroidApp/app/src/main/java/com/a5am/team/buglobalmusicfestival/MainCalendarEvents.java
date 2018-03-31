package com.a5am.team.buglobalmusicfestival;

/**
 * Created by sophiahon on 3/31/18.
 */

public class MainCalendarEvents {

    //Store the name of the artist
    private String name;

    //Store the country the artist is from
    private String country;

    //Store the venue
    private String venue;

    //Constructor that is used to create an instance of the Event object
    public MainCalendarEvents(String name, String country, String Venue){
        this.name = name;
        this.country = country;
        this.venue = venue;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getVenue(){
        return venue;
    }

    public void setVenue(String venue){
        this.venue = venue;
    }

}
