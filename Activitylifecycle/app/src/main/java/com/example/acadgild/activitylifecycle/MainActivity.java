package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("OnCreate", "This is the on create method the UI for the application.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStartUp","This is the on start method which is brought on the frontend but not yet activated");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","Get executed continuously, executing the frontend and displaying the output to the user, both interactive and visible");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("","Memory allocated is not deleted from the RAM");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","This is the on stop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("", "De allocated");
    }

    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
