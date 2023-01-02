package com.example.myapplication;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity1 extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoview;
    private Button start;
    private Button pause;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer1);
        initView();
    }

    private void initView() {
        videoview = (VideoView) findViewById(R.id.videoview);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.jntm);
        videoview.setVideoURI(uri);
        videoview.requestFocus();
        //自动开始
        videoview.start();
        //完成提示
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MusicActivity1.this,"播放完成",Toast.LENGTH_SHORT).show();
            }
        });

        start.setOnClickListener(this);
        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
        handler.postDelayed(runnable,500);
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int position=videoview.getCurrentPosition();
            int totalduration=videoview.getDuration();
            if(videoview.isPlaying()){
                seekBar.setMax(totalduration);
                seekBar.setProgress(position);
            }
            handler.postDelayed(runnable,500);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                videoview.start();
                if (videoview.isPlaying()){
                    pause.setEnabled(true);
                }else {
                    pause.setEnabled(false);
                }
                break;
            case R.id.pause:
                videoview.pause();
                if (videoview.isPlaying()){
                    start.setEnabled(false);
                }else {
                    start.setEnabled(true);
                }
                break;
        }
    }
}

