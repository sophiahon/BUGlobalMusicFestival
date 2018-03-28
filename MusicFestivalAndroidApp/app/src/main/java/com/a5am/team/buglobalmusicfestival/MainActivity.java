package com.a5am.team.buglobalmusicfestival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.a5am.team.buglobalmusicfestival.DateBase.DatabaseExecute;
import com.a5am.team.buglobalmusicfestival.DateBase.Event;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MAIN";
    private TextView txtShow;
    private ListView listViewDatabase;

    private DatabaseExecute eventExe;
    private List<Event> eventList;

    private EventLIstViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventExe = new DatabaseExecute(this);
        if (! eventExe.isDataExist()){
            eventExe.initTable();
        }

        initComponent();

        eventList = eventExe.getAllDate();
        if (eventList != null){
            listAdapter = new EventLIstViewAdapter(this, eventList);
            listViewDatabase.setAdapter(listAdapter);
        }
    }

    private void initComponent(){

        // listViewDatabase is used to check database
        txtShow = findViewById(R.id.txtShow);
        listViewDatabase = findViewById(R.id.listViewDatabase);
        listViewDatabase.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_sql_data, null), null,
                false);

    }


}
