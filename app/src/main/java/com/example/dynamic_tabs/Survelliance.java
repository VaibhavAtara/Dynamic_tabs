package com.example.dynamic_tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class Survelliance extends AppCompatActivity {
    ProgressDialog progressDialog;
    VideoView videoView;
    ImageButton imageButton;
    boolean isPlaying;

    String videoUrl = "H:\\Songs\\dekh_lena.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survelliance);

        videoView = (VideoView) findViewById(R.id.videoStream);
        imageButton = (ImageButton) findViewById(R.id.btnPlayPause);
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
        });

    }

}
