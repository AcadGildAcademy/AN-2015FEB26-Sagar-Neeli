package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sneeli on 3/23/2015.
 */
public class AppsActivity extends Activity implements View.OnClickListener{
    Button specificApp, developer, search;
    EditText searchApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        specificApp = (Button) findViewById(R.id.buttonSpecificApp);
        developer = (Button) findViewById(R.id.buttonDeveloper);
        search = (Button) findViewById(R.id.buttonSearch);
        searchApp = (EditText) findViewById(R.id.editSearch);

        specificApp.setOnClickListener(this);
        developer.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.buttonSpecificApp:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=ranjith.naidu.filetransfer.gui")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=ranjith.naidu.filetransfer.gui")));
                }
                break;
            case R.id.buttonDeveloper:
                Intent page3 = new Intent(this,SettingsActivity.class);
                startActivity(page3);
                break;
            case R.id.buttonSearch:
                String search_query = searchApp.getText().toString();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=" + search_query + "&c=apps")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/search?q=" + search_query + "&c=apps")));
                }
                break;
        }
    }
}
