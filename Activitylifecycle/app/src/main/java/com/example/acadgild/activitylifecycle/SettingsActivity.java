package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

/**
 * Created by sneeli on 3/23/2015.
 */
public class SettingsActivity extends Activity implements View.OnClickListener{
    Button wifi, bluetooth, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        wifi = (Button) findViewById(R.id.buttonWifi);
        bluetooth = (Button) findViewById(R.id.buttonBluetooth);
        account = (Button) findViewById(R.id.buttonAccount);

        wifi.setOnClickListener(this);
        bluetooth.setOnClickListener(this);
        account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.buttonWifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.buttonBluetooth:
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
            case R.id.buttonAccount:
                startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT));
                break;
        }
    }
}
