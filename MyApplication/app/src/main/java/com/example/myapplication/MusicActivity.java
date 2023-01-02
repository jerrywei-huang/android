package com.example.myapplication;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private SeekBar sb;

    private int duration;
    private Button playbtn, pausebtn, stopbtn;
    // 播放器的几个状态
    private static final int PLAYING = 1;// 播放状态
    private static final int PAUSING = 2;// 暂停状态
    private static final int STOPPING = 3;// 停止状态
    private volatile int CURRENT = 0;// 当前状态
    private Timer timer;
    private static MediaPlayer mp = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);
        playbtn = findViewById(R.id.playbtn);
        pausebtn = findViewById(R.id.pausebtn);
        stopbtn = findViewById(R.id.stopbtn);
        pausebtn.setOnClickListener(this);
        playbtn.setOnClickListener(this);
        stopbtn.setOnClickListener(this);
        sb = (SeekBar) findViewById(R.id.sb);

        //设置拖动监听
        sb.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playbtn:


                mp = MediaPlayer.create(this, R.raw.t1);
                //设置音乐格式
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //准备
                mp.start();
                mp.setLooping(true);
                //获取音乐最大长度（毫秒单位）
                duration = mp.getDuration();
                //给SeekBar 设置最大值
                sb.setMax(duration);
                //音乐开始播放
                mp.start();
                //设置当前的状态为播放

                if (timer == null) {
                    //创建定时器
                    timer = new Timer();
                }
                /**
                 * 参数1：匿名内部类，相当于Runnable 类
                 * 参数2：第一次延时多长时间（毫秒）后执行，0 则代表立即执行
                 * 参数3：每隔多长时间(毫秒)执行一次
                 */
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {//该方法每1 秒被调用一次
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mp!= null){
                                    int currentPosition = mp.getCurrentPosition();
                                    sb.setProgress(currentPosition);
                                }
                            }
                        });

                    }
                }, 0, 1000);


                break;
            case R.id.pausebtn:
                mp.pause();
                break;
            case R.id.stopbtn:
                mp.stop();
                sb.setProgress(0);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }

    }

    /*
     * 拖动过程中回调多次
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mp == null) {
            sb.setProgress(0);
        } else {
            mp.seekTo(progress);
        }
    }

    /*
     * 开始拖动前回调一次
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (mp == null) {
            Toast.makeText(this, "音乐播放器还未开始", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * 结束拖动后回调一次
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}


