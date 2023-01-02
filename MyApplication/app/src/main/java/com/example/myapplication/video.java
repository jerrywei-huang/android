package com.example.myapplication;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity  {
    private VideoView videoView;
    private Button start;
    private SeekBar seekBar;
    private Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thelast);
        initView();
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.videoview);
        start = (Button) findViewById(R.id.start);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //文件路径
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.jntm);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        //自动开始
        videoView.start();
        ;
        //完成提示
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(video.this, "播放完成", Toast.LENGTH_SHORT).show();

            }
        });


        pause = (Button) findViewById(R.id.pause);



        handler.postDelayed(runnable, 500);
    }

    //线程刷新
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int position = videoView.getCurrentPosition();
            int totalduration = videoView.getDuration();
            if (videoView.isPlaying()) {
                seekBar.setMax(totalduration);
                seekBar.setProgress(position);
            }
            handler.postDelayed(runnable, 500);
        }
    };

    public void bofang(View v) {
        videoView.start();
        pause.setVisibility(View.VISIBLE);
        start.setVisibility(View.GONE);
    }

    public void tingzhi(View v) {
        videoView.pause();
        start.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);
    }





    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.activity_thelast, menu);
        return true;
    }

}


