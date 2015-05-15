package com.example.neeli.sagar.broadcastreceiverandnotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private static final String FROM_ACTIVITY = "com.example.neeli.sagar.broadcastreceiverandnotifications.MainActivity";
    private static final int NOTIFICATION_ID = 1009;
    ToggleButton toggle;
    CheckBox checkBox;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggle = (ToggleButton) findViewById(R.id.toggleButtonWifi);
        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        checkBox = (CheckBox) findViewById(R.id.checkBoxBroadcast);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true && !wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(isChecked);
                    Toast.makeText(getApplicationContext(), "Wifi is enabled", Toast.LENGTH_SHORT).show();
                } else if (isChecked == false && wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(isChecked);
                    Toast.makeText(getApplicationContext(), "Wifi is disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (((CheckBox) view).isChecked()) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder.setContentTitle("Battery Level");
            notificationBuilder.setSmallIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            notificationBuilder.setContentText("Check Battery Percentage");
            notificationBuilder.setAutoCancel(true);

//            Intent intent = new Intent(FROM_ACTIVITY);
//            sendBroadcast(intent);
//            MyBroadCastReceiver receiver = new MyBroadCastReceiver();
//            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
//            notificationBuilder.setContentIntent(pendingIntent);
//            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

            Intent intent = new Intent(this, MyBroadCastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            notificationBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

//            IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//            registerReceiver(receiver, batteryLevelFilter);
        }
        else if (!((CheckBox) view).isChecked()) {
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }
}
