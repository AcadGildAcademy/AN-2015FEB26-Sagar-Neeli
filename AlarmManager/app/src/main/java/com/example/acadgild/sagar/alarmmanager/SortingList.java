package com.example.acadgild.sagar.alarmmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SortingList extends Activity implements View.OnClickListener{

    ListView lv;
    ArrayAdapter<String> aa;
    Button asc, desc;
    String data[] = {"Jan", "Feb", "March", "April", "May", "June", "July"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_list);
        lv = (ListView)findViewById(R.id.months);
        asc = (Button)findViewById(R.id.buttonAscending);
        desc = (Button)findViewById(R.id.buttonDescending);
        aa = new ArrayAdapter<String>(this,android.R.layout.activity_list_item,android.R.id.text1,data);
        lv.setAdapter(aa);
        asc.setOnClickListener(this);
        desc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAscending:
                Arrays.sort(data);
                aa.notifyDataSetChanged();
                break;
            case R.id.buttonDescending:
                Arrays.sort(data);
                List<String> list = Arrays.asList(data);
                Collections.reverse(list);
                aa.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sorting_list, menu);
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
}
