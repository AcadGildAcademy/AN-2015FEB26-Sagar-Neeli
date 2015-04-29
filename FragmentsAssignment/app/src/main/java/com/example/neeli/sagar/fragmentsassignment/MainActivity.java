package com.example.neeli.sagar.fragmentsassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.button1:
                fragmentManager.popBackStack();
                FirstFragment firstFragment = new FirstFragment();
//                fragmentTransaction.replace(android.R.id.content, firstFragment);
                fragmentTransaction.add(R.id.frag_one, firstFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.button2:
                fragmentManager.popBackStack();
                SecondFragment secondFragment = new SecondFragment();
//                fragmentTransaction.replace(android.R.id.content, secondFragment);
                fragmentTransaction.add(R.id.frag_two, secondFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.button3:
                fragmentManager.popBackStack();
                ThirdFragment thirdFragment = new ThirdFragment();
//                fragmentTransaction.replace(android.R.id.content, thirdFragment);
                fragmentTransaction.add(R.id.frag_three, thirdFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }

    }
}
