package com.a5am.team.buglobalmusicfestival.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a5am.team.buglobalmusicfestival.R;

import java.util.List;

/**
 * Created by pro on 2018/3/28.
 *
 * This is the ListView adapter for showing the database.
 *
 * It's purpose is to test our database, check whether every database relate info is right
 *
 */

public class DatabaseTestListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventList;


    public static class ViewHolder{
        public TextView txtID;
        public TextView txtEvent;
        public TextView txtArtist;
        public TextView txtPlace;
        public TextView txtDate;
        public TextView txtArtCountry;
    }


    public DatabaseTestListViewAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Event event = eventList.get(position);
        if (event == null){
            return null;
        }

        ViewHolder holder = null;
        if (view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.database_test_list, null);

            holder = new ViewHolder();
            holder.txtID =  view.findViewById(R.id.txtID);
            holder.txtEvent = view.findViewById(R.id.txtEvent);
            holder.txtArtist = view.findViewById(R.id.txtArtist);
            holder.txtArtCountry = view.findViewById(R.id.txtArtCountry);
            holder.txtPlace = view.findViewById(R.id.txtPlace);
            holder.txtDate = view.findViewById(R.id.txtDate);

            view.setTag(holder);
        }

        holder.txtID.setText(event.id + "");
        holder.txtEvent.setText(event.event);
        holder.txtArtist.setText(event.artist.artist);
        holder.txtArtCountry.setText(event.artist.country);
        holder.txtPlace.setText(event.place);
        holder.txtDate.setText(event.date);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }





}
