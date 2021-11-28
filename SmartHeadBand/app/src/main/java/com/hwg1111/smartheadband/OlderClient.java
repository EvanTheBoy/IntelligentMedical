package com.hwg1111.smartheadband;

import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class OlderClient extends Thread {

    private Socket socketClient;
    private InputStream input;
    private OutputStream output;
    private DataInputStream dinput;
    private DataOutputStream doutput;
    private String latitude=null;
    private String longitude=null;
    private  AMapLocationClient client;
    //服务线程构造器
    public OlderClient(AMapLocationClient client){
        this.client = client;
    }

    public void run(){
        setClient();
        while(true){
            if(client.isStarted()) {
                AMapLocation location = client.getLastKnownLocation();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                sentMessage(latitude+"",longitude+"");
               // text.append(latitude+" "+longitude);
            }
            //每10秒发一次位置
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setClient(){
        try {
            socketClient = new Socket("192.168.50.221",10000);
            input=socketClient.getInputStream();
            dinput=new DataInputStream(input);
            output=socketClient.getOutputStream();
            doutput=new DataOutputStream(output);
            //发送marker 表明这是老人端
            output.write(1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void sentMessage(String latitude,String longitude){
          try{
              if(latitude!=null && longitude!=null) {
                  //发送数据类型，1表示接下来的数据为经纬度
                  output.write(1);
                  //传经度
                  byte[] msg1 = latitude.getBytes();
                  output.write(msg1.length);
                  output.write(msg1);

                  //传纬度
                  byte[] msg2 = longitude.getBytes();
                  output.write(msg2.length);
                  output.write(msg2);
                  output.flush();
              }
          }catch (IOException e){
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
    }

}
