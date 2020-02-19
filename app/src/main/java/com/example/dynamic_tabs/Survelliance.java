package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Survelliance extends AppCompatActivity {
    ProgressDialog progressDialog;
    WebView videoView;
    ImageButton imageButton;
    boolean isPlaying;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    String videoUrl = "http://www.youtube.com/watch?v=ZuG5VuWGad8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survelliance);
        /*Button set_alarm=(Button)findViewById(R.id.set_alarm);
       /*videoView = (WebView) findViewById(R.id.videoStream);
       videoView.setWebViewClient(new WebViewClient());
       videoView.loadUrl(videoUrl);*/
        /*alarmMgr=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //Intent intent=new Intent(this,AlarmReceiver.class);
        //alarmIntent=PendingIntent.getBroadcast(Survelliance.this,0,intent,0);
       set_alarm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime()+AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                    AlarmManager.INTERVAL_FIFTEEN_MINUTES,alarmIntent);
            Toast.makeText(getApplicationContext(),"Alarm set",Toast.LENGTH_LONG).show();
           }
       });*/


       /* imageButton = (ImageButton) findViewById(R.id.btnPlayPause);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoUrl));
        //videoView.setVideoPath(videoUrl);
        videoView.requestFocus();
        videoView.start();
        isPlaying = true;
        imageButton.setImageResource(R.drawable.ic_pause);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPlaying)
                {
                    videoView.pause();
                    isPlaying=false;
                    imageButton.setImageResource(R.drawable.ic_play);
                }
                else
                {
                    videoView.start();
                    isPlaying=true;
                    imageButton.setImageResource(R.drawable.ic_pause);

                }
            }
        });*/

    }

}
