package com.a5am.team.buglobalmusicfestival;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.a5am.team.buglobalmusicfestival.Database.DatabaseExecute;
import com.a5am.team.buglobalmusicfestival.Database.DatabaseTestActivity;
import com.a5am.team.buglobalmusicfestival.Database.Event;
import com.a5am.team.buglobalmusicfestival.Database.DatabaseTestListViewAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This is the launcher activity.
 *
 * The database test is now moving to Database package with it's own activity and layout
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MAIN";
    private Button btnDatabaseTest;

    private DatabaseExecute eventExe;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init database
        eventExe = new DatabaseExecute(this);
        if (! eventExe.isDataExist()){
            eventExe.initTable();
        }

        // init component
        initComponent();

        // fetch all data and trans to Event model
        eventList = eventExe.getAllDate();
    }

    private void initComponent(){
        btnDatabaseTest = findViewById(R.id.btnDatabaseTest);
        btnDatabaseTest.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDatabaseTest:
                Intent showDatabaseTestActivity = new Intent(getBaseContext(),
                        DatabaseTestActivity.class);
                if (eventList != null) {
                    showDatabaseTestActivity.putExtra("db", (Serializable) eventList);
                }
                startActivity(showDatabaseTestActivity);
        }
    }
}
