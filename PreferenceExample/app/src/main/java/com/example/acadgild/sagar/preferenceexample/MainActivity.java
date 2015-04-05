package com.example.acadgild.sagar.preferenceexample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sneeli on 4/5/2015.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private static final int SETTINGS_RESULT = 1;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.buttonSettings);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), UserPreference.class);
        startActivityForResult(i, SETTINGS_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_RESULT)
            displayUserSettings();
    }

    private void displayUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settingsStr = "";

        settingsStr = settingsStr + "Password : " + sharedPrefs.getString("prefUserPassword", "NOPASSWORD");
        settingsStr = settingsStr + "\nRemind For Update : " + sharedPrefs.getBoolean("prefUserLock", false);
        settingsStr = settingsStr + "\nUpdate Frequency : " + sharedPrefs.getString("prefUpdateFrequency", "NOUPDATE");
        TextView  textViewSetting = (TextView) findViewById(R.id.textViewSettings);
        textViewSetting.setText(settingsStr);

    }
}
