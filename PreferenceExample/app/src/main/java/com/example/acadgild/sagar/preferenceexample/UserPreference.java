package com.example.acadgild.sagar.preferenceexample;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by sneeli on 4/2/2015.
 */
public class UserPreference extends PreferenceActivity{

    @SuppressWarnings("deprecations")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
    }

}
