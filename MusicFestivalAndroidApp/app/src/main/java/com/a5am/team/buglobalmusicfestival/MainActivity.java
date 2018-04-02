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

//for spotify
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;



/**
 * This is the launcher activity.
 *
 * The database test is now moving to Database package with it's own activity and layout
 *
 * Extends BaseNavActivity for Nav Drawer
 *
 */


public class MainActivity extends BaseNavActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback{


    private static final String TAG = "MAIN";

    private DatabaseExecute eventExe;
    public static List<Event> eventList;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout mFrame;

    private static final String CLIENT_ID = "11dc844e73034ce09b80e978c27a6931";
    private static final String REDIRECT_URI = "myapp://callback";
    private Player mPlayer;
    private static final int REQUEST_CODE = 1337;

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

        //Spotify authentication
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }


    private void initComponent(){
        // Component Init in here
    }


/////This is the code for Spotify

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(MainActivity.this);
                        mPlayer.addNotificationCallback(MainActivity.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
        mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Error var1) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
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
