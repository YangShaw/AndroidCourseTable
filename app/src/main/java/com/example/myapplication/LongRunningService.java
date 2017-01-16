package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class LongRunningService extends Service {

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder=new Notification.Builder(LongRunningService.this);
                builder.setContentTitle("上课提醒");
                builder.setContentText("还有十分钟就要上课了");
                builder.setWhen(System.currentTimeMillis());
                builder.setSmallIcon(R.mipmap.ic_launcher);
                Notification notification=builder.build();
                long[] ss={0,1000,1000,1000};
                notification.vibrate=ss;

                manager.notify(1,notification);

            }
        }).start();


        return super.onStartCommand(intent,flags,startId);

    }


}
