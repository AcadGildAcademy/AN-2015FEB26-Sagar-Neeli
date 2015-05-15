package com.example.neeli.sagar.broadcastreceiverandnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Created by sneeli on 5/15/2015.
 */
public class MyBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        context.unregisterReceiver(this);
        intent = context.getApplicationContext().registerReceiver(null, new IntentFilter((Intent.ACTION_BATTERY_CHANGED)));
        int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int level = -1;
        if (currentLevel >= 0 && scale > 0) {
            level = (currentLevel * 100) / scale;
        }

        intent.setClass(context.getApplicationContext(), BatteryPercentage.class);
        intent.putExtra("batteryPercentage", level);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

//        Bundle bundle = getIntent().getExtras();
//        String message = bundle.getString("message");
        //        Intent battery = new Intent(context, BatteryPercentage.class);
////        Intent battery = new Intent(context.getApplicationContext(), BatteryPercentage.class);
//        battery.putExtra("batteryPercentage", level);
////        battery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        battery.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(battery);
//        Log.d("The batttery percentage :", level);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    }
}
