package com.a5am.team.buglobalmusicfestival;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a5am.team.buglobalmusicfestival.Database.Event;

import java.util.List;

/**
 * Created by sophiahon on 3/31/18.
 */


public class MainCalendarAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventList;

    public MainCalendarAdapter(Context context, List<Event> eventList){
//        super(context, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    public static class ViewHolder{
        public TextView eventName;
        public TextView artist;
        public TextView country;
        public TextView place;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.mainevent_list, parent, false);
        }

        Event currentEvent = eventList.get(position);

        TextView event = (TextView) listItem.findViewById(R.id.tv_event);
        event.setText(currentEvent.event);

        TextView artist = (TextView) listItem.findViewById(R.id.tv_artist);
        artist.setText(currentEvent.artist.artist);

        TextView country = (TextView) listItem.findViewById(R.id.tv_country);
        country.setText(currentEvent.artist.country);

        TextView venue = (TextView) listItem.findViewById(R.id.tv_place);
        venue.setText(currentEvent.place);

        return  listItem;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

}
