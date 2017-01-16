package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context,Intent intent){
        Intent i=new Intent(context,LongRunningService.class);

        Toast.makeText(context,"到了上课的时间了",Toast.LENGTH_SHORT).show();
        context.startService(i);

    }
}
