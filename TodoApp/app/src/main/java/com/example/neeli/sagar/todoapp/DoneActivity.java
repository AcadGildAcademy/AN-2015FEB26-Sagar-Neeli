package com.example.neeli.sagar.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

/**
 * Created by sneeli on 4/23/2015.
 */
public class DoneActivity extends ActionBarActivity {

    ToDoItemDatabase completedDb;
    private Toolbar toolbar;
    ListView listView;
    List<ToDoItem> items;
    List<ToDoItem> results;
    CompletedTodoCustomAdapter adapter;
    ToDoItem m;
    private static String COMPLETED_TODO = "COMPLETED_TODO";
//    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        // set the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(android.R.id.list);
        completedDb = new ToDoItemDatabase(getApplicationContext(), COMPLETED_TODO);

//        for(ToDoItem item : MainActivity.getTodoItemsList())
//                completedDb.insertIntoTable(item);
        Log.e("DoneActivity results", String.valueOf(MainActivity.getTodoItemsList()));
        items = completedDb.getAllTasks();
        adapter = new CompletedTodoCustomAdapter(this, MainActivity.getTodoItemsList());
//        adapter = new CompletedTodoCustomAdapter(this, items);
        listView.setAdapter(adapter);

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
        else if(id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
