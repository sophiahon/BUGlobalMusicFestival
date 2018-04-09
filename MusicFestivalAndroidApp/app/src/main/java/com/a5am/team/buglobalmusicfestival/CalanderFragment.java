package com.a5am.team.buglobalmusicfestival;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a5am.team.buglobalmusicfestival.Database.DatabaseExecute;
import com.a5am.team.buglobalmusicfestival.Database.Event;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalanderFragment extends Fragment {

    private ListView LV;
    private MainCalendarAdapter mcAdapter;
    private DatabaseExecute eventExe;
    public static List<Event> eventList;


    public CalanderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calander, container, false);
        LV = view.findViewById(R.id.mcEventList);
        eventExe = new DatabaseExecute(getContext());

        if (!eventExe.isDataExist()) {
            eventExe.initTable();
        }

        eventList = eventExe.getAllDate();

        if (eventList != null){
            mcAdapter = new MainCalendarAdapter(getContext(), eventList);
            LV.setAdapter(mcAdapter);
        }
        return view;
    }

}
