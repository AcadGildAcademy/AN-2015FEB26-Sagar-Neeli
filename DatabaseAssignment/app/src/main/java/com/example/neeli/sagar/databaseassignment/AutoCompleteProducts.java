package com.example.neeli.sagar.databaseassignment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class AutoCompleteProducts extends ActionBarActivity {

    private SQLiteitemSearch productsDb;
    private AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_products);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        productsDb = new SQLiteitemSearch(AutoCompleteProducts.this);
        productsDb.openDB();
        productsDb.insertItemSearch("Color Monitor");
        productsDb.insertItemSearch("Compact Disk");
        productsDb.insertItemSearch("Computer");
        productsDb.insertItemSearch("Hard Disk");
        productsDb.insertItemSearch("HP Printer");
        productsDb.insertItemSearch("HP Laser Printer");
        productsDb.insertItemSearch("HP Injet Printer");
        final String[] deal = productsDb.getAllItemFilter();

        // Print out the values to the log
        for (int i = 0; i < deal.length; i++) {
            Log.i(this.toString(), deal[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, deal);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                arg0.getItemAtPosition(arg2);
                Log.i("SELECTED TEXT WAS----->", deal[arg2]);
            }
        });
        productsDb.close();
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auto_complete_products, menu);
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
