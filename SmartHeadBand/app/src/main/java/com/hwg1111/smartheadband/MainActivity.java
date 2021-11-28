package com.hwg1111.smartheadband;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps2d.MapsInitializer;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除页面上方视图框
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        AMapLocationClient.updatePrivacyShow(MainActivity.this,true,true);
        AMapLocationClient.updatePrivacyAgree(MainActivity.this,true);

        checkPermisson();
        initButton();
        initBackground();
    }
    //初始化按钮并添加按钮监听器
    private void initButton(){
        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }


    String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION};

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermisson(){
        boolean flag=true;//默认全部被申请过
        for(int i=0;i<permissions.length;i++){//只要有一个没有申请成功
            if(!(ActivityCompat.checkSelfPermission(this,permissions[i])== PackageManager.PERMISSION_GRANTED)){
                flag=false;
            }
        }
        if(!flag){
            //动态申请权限
            requestPermissions(permissions,100);
        }
    }


    //设置app主页面的视频背景
    private void initBackground(){
        videoview = (VideoView)findViewById(R.id.video_background);
        //设置video的路径
        String videopath = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background).toString();
        videoview.setVideoPath(videopath);
        videoview.start();
        //设置video循环播放
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.setVideoPath(videopath);
                videoview.start();
            }
        });
    }
}