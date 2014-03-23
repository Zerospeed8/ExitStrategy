package com.zerospeed8.exitstrategy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent){
        {
            Intent service1 = new Intent(context, MyAlarm.class);
            context.startService(service1);

        }

    }
}