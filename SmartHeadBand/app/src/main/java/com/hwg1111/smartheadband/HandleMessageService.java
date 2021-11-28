package com.hwg1111.smartheadband;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class HandleMessageService extends Service {
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }


    public void onCreate(){
    super.onCreate();

    }

    public void onDestroy(){
    super.onDestroy();
    }



    public int onStartCommand(Intent intent, int flags, int startId) {


      return super.onStartCommand(intent,flags,startId);
    }





}
