package com.example.intellectualmedicaltreatment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.amap.api.location.AMapLocationClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


//监听定位和定位变化
public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingButton;

    private final String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(){
        boolean flag=true;//默认全部被申请过
        for (String permission : permissions) {//只要有一个没有申请成功
            if (!(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)) {
                flag = false;
            }
        }
        if(!flag){
            //动态申请权限
            requestPermissions(permissions,100);
        }
    }

    private void initFloatButton() {
        floatingButton = (FloatingActionButton) this.findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        AMapLocationClient.updatePrivacyShow(MainActivity.this,true,true);
        AMapLocationClient.updatePrivacyAgree(MainActivity.this,true);
        checkPermission();
        initFloatButton();
    }
}
