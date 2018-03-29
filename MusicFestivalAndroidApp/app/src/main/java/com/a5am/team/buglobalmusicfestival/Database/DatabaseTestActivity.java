package com.a5am.team.buglobalmusicfestival.Database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.a5am.team.buglobalmusicfestival.R;

import java.util.List;

/**
 * Created by pro on 2018/3/29.
 *
 * This is the Activity for database test
 */

public class DatabaseTestActivity extends AppCompatActivity {

    private static final String TAG = "DB TEST";
    private ListView listViewDatabase;

    private DatabaseExecute eventExe;
    private List<Event> eventList;

    private DatabaseTestListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_test);

        initComponent();

        eventList = (List<Event>) getIntent().getSerializableExtra("db");
        if (eventList != null){
            listAdapter = new DatabaseTestListViewAdapter(this, eventList);
            listViewDatabase.setAdapter(listAdapter);
        }
    }

    private void initComponent(){
        // listViewDatabase is used to check database
        listViewDatabase = findViewById(R.id.listViewDatabase);
        listViewDatabase.addHeaderView(LayoutInflater.from(this).inflate(R.layout.database_test_list, null), null,
                false);
    }
}
