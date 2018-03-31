package com.a5am.team.buglobalmusicfestival;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
 *
 * Extends BaseNavActivity for Nav Drawer
 *
 */


public class MainActivity extends BaseNavActivity {


    private static final String TAG = "MAIN";

    private DatabaseExecute eventExe;
    public static List<Event> eventList;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout mFrame;

    private ListView listView;
    private MainCalendarAdapter mcAdapter;

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
        initializeToolbar();

        // fetch all data
        eventList = eventExe.getAllDate();

//        listView = (ListView) findViewById(R.id.mcEventList);
//        ArrayList<MainCalendarEvents> eventList = new ArrayList<>();
//        eventList.add(new MainCalendarEvents("Jupiter & Okwess", "Country: Congo", "GSU"));
//        eventList.add(new MainCalendarEvents("Ladama", "Country: Latin America - Brazil, Colombia, Venezuela", "GSU"));
//        eventList.add(new MainCalendarEvents("Dina Elwedidi", "Country: Egypt", "CAS"));
//        eventList.add(new MainCalendarEvents("Orquesta El Macabeo", "Country: Puerto Rico", "GSU"));
//        eventList.add(new MainCalendarEvents("Zhou Family", "Country: China", "GSU"));
//        eventList.add(new MainCalendarEvents("Kaumakaiwa Kanaka'ole", "Country: Hawai'i", "CAS"));

        if (eventList != null){
            mcAdapter = new MainCalendarAdapter(this, eventList);
            listView.setAdapter(mcAdapter);
        }
    }


    private void initComponent(){
        // listViewDatabase is used to check database
        listView = findViewById(R.id.mcEventList);
    }


    /**
     * Previous trying of nav Drawer.
     * Don't delete for now
     * All now in BaseNavActivity
     */
//    protected void initToolbar(){
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mFrame = (FrameLayout)findViewById(R.id.content_frame);
//        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        // Try to set nav to half of the screen width:
//        int width = getResources().getDisplayMetrics().widthPixels/2;
//        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mFrame.getLayoutParams();
//        params.width = width;
//        mFrame.setLayoutParams(params);
//    }
//
//
//
//
//    private void setNavigationViewListner() {
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        // Handle navigation view item clicks here.
//        switch (item.getItemId()) {
//
//            case R.id.nav_databaseTest: {
//                showDatabaseActivity();
//                break;
//            }
//        }
//        //close navigation drawer
//        mDrawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//
//    /**
//     * Menu option selected action
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(mToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    private void showDatabaseActivity(){
//        Intent showDB = new Intent(getBaseContext(), DatabaseTestActivity.class);
//        if (eventList != null) {
//            showDB.putExtra("db", (Serializable) eventList);
//        }
//        startActivity(showDB);
//    }

}
