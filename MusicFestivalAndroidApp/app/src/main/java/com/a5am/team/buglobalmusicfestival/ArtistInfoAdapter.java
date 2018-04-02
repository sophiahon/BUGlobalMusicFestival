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
 * Created by pro on 2018/3/31.
 */

public class ArtistInfoAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventList;

    public ArtistInfoAdapter(Context context, List<Event> eventList){
//        super(context, eventList);
        this.context = context;
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.artist_list, parent, false);
        }

        Event currentEvent = eventList.get(position);

        TextView event = (TextView) listItem.findViewById(R.id.tv_artist);
        event.setText(currentEvent.artist.artist);

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
