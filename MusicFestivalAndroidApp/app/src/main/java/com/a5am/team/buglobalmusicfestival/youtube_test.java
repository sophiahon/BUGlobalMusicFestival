package com.a5am.team.buglobalmusicfestival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;



public class youtube_test extends YouTubeBaseActivity{

    private Button btn;
    private YouTubePlayerView ytb;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private final String API_KEY = "AIzaSyBOitEsuiC4-Hbh8EJOMhAPIbJ0tQ8GS3U";
    private final String VIDEO_CODE ="zTz1v8aJeKg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_test);


        btn = findViewById(R.id.btn);
        ytb = (YouTubePlayerView) findViewById(R.id.ytb);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(VIDEO_CODE);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(youtube_test.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ytb.initialize(API_KEY, onInitializedListener);
            }
        });
    }

}
