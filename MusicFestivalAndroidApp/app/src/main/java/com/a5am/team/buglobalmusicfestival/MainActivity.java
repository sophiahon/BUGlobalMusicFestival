package com.a5am.team.buglobalmusicfestival;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MAIN";
    private Button btnDatabaseTest;

    private DatabaseExecute eventExe;
    private List<Event> eventList;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout mFrame;


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
//        initComponent();

        // fetch all data and trans to Event model
        eventList = eventExe.getAllDate();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFrame = (FrameLayout)findViewById(R.id.content_frame);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        int width = getResources().getDisplayMetrics().widthPixels/2;
//        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mFrame.getLayoutParams();
//        params.width = width;
//        mFrame.setLayoutParams(params);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
    //    private void initComponent(){
////        btnDatabaseTest = findViewById(R.id.btnDatabaseTest);
////        btnDatabaseTest.setOnClickListener(this);
//    }





//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnDatabaseTest:
//                Intent showDatabaseTestActivity = new Intent(getBaseContext(),
//                        DatabaseTestActivity.class);
//                if (eventList != null) {
//                    showDatabaseTestActivity.putExtra("db", (Serializable) eventList);
//                }
//                startActivity(showDatabaseTestActivity);
//        }
//    }


}
