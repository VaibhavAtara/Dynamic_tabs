package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class Survelliance extends AppCompatActivity {
    ProgressDialog progressDialog;
    WebView videoView;
    ImageButton imageButton;
    boolean isPlaying;

    String videoUrl = "http://www.youtube.com/watch?v=ZuG5VuWGad8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survelliance);

       videoView = (WebView) findViewById(R.id.videoStream);
       videoView.setWebViewClient(new WebViewClient());
       videoView.loadUrl(videoUrl);
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
