package com.a5am.team.buglobalmusicfestival;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.a5am.team.buglobalmusicfestival.Database.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sophiahon on 3/31/18.
 */


public class MainCalendarAdapter extends ArrayAdapter<MainCalendarEvents>{

    private Context mc_Context;
    private List<MainCalendarEvents> mc_Events = new ArrayList<>();

    public MainCalendarAdapter(@NonNull Context context, ArrayList<MainCalendarEvents> list){
        super(context, 0, list);
        mc_Context = context;
        mc_Events = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mc_Context).inflate(R.layout.mc_eventlist, parent, false);
        }
        MainCalendarEvents currentEvent = mc_Events.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.tv_name);
        name.setText(currentEvent.getName());

        TextView country = (TextView) listItem.findViewById(R.id.tv_country);
        country.setText(currentEvent.getCountry());

        TextView venue = (TextView) listItem.findViewById(R.id.tv_venues);
        venue.setText(currentEvent.getVenue());

        return  listItem;
    }

}
