package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

/**
 * Created by sneeli on 3/23/2015.
 */
public class HomeActivity extends Activity implements View.OnClickListener{
    Button apps, settings, vibrate;
//    private Activity context;
//    Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE) ;
    private Vibrator vibrateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apps = (Button) findViewById(R.id.buttonApps);
        settings = (Button) findViewById(R.id.buttonSettings);
        vibrate = (Button) findViewById(R.id.buttonVibrate);

        vibrateButton = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        apps.setOnClickListener(this);
        settings.setOnClickListener(this);
        vibrate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        switch(id){
            case R.id.buttonApps:
                Intent appsActivity = new Intent(this, AppsActivity.class);
                startActivity(appsActivity);
                break;
            case R.id.buttonSettings:
                Intent settingsActivity = new Intent(this,SettingsActivity.class);
                startActivity(settingsActivity);
                break;
            case R.id.buttonVibrate:
                vibrateButton.vibrate(10);
                break;
        }
    }
}
