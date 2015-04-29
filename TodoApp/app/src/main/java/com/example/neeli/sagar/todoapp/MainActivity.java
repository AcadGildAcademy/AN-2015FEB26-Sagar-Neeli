package com.example.neeli.sagar.todoapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, OnItemClickListener {

    String TITLES[] = {"Home", "Events", "Mail", "Shop", "Travel"};
    int ICONS[] = {R.drawable.ic_home, R.drawable.ic_events, R.drawable.ic_mail, R.drawable.ic_shop, R.drawable.ic_travel};
    ImageButton FAB;
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Sagar Neeli";
    String EMAIL = "sagarneeli119@gmail.com";
    int PROFILE = R.drawable.neo;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    Calendar calendar;
    int day;
    int month;
    int year;


    ListView listView;
    List<ToDoItem> items;
    CustomListAdapter adapter;
    ToDoItemDatabase db;
    ToDoItemDatabase completedDb;
    DoneActivity done;
//    Intent intent;
    private static List<ToDoItem> results;
    private static String TODO = "TODO";
    private static String COMPLETED_TODO = "COMPLETED_TODO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.hide();

        // set the Navigation app drawer
        mRecyclerView = (RecyclerView) findViewById(R.id.NavigationRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        results = new ArrayList<ToDoItem>();
        // List view on the main app activity
//        listView = (ListView) findViewById(android.R.id.list);
        listView = (ListView) findViewById(R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(this);
        Set_Referash_Data();

    }

    public void Set_Referash_Data() {
//        db = new TaskDBHelper(MainActivity.this);
        db = new ToDoItemDatabase(getApplicationContext(), TODO);
//        db = new ToDoItemDatabase(MainActivity.this, TODO);
        items = db.getAllTasks();
        adapter = new CustomListAdapter(this, items);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Set_Referash_Data();

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
        else if (id == R.id.action_next) {
            Intent intent = new Intent(this, DoneActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                LayoutInflater inflater = LayoutInflater.from(this);
                View promtView = inflater.inflate(R.layout.custom_dialog, null);
                final AlertDialog dialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();

                final EditText title = (EditText) promtView.findViewById(R.id.editTextTitle);
                final EditText description = (EditText) promtView.findViewById(R.id.editTextDescription);
                final DatePicker datePicker = (DatePicker) promtView.findViewById(R.id.datePicker);

                Button dialogButtonSave = (Button) promtView.findViewById(R.id.buttonSave);
                dialogButtonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (title.getText().toString().equals("") || description.getText().toString().equals("")) {
                                String str = "Don't Leave any field blank !";
                                Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            } else {
                                String titleInput = title.getText().toString();
                                String descriptionInput = description.getText().toString();
                                Toast toast = Toast.makeText(MainActivity.this, titleInput, Toast.LENGTH_SHORT);
                                day = datePicker.getDayOfMonth();
                                month = datePicker.getMonth() + 1;
                                year = datePicker.getYear();
                                String date = year + "/" + month + "/" + day;
                                ToDoItem r = new ToDoItem();
                                r.setDate(date);
                                r.setTitle(titleInput);
                                r.setDescription(descriptionInput);
                                db.insertIntoTable(r);
                                db = new ToDoItemDatabase(getApplicationContext(), TODO);
//                                db = new ToDoItemDatabase(MainActivity.this, TODO);
                                Set_Referash_Data();
                                dialog.dismiss();
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getStackTrace());
                        }
                    }
                });
                Button dialogButtonCancel = (Button) promtView.findViewById(R.id.buttonCancel);
                // if button is clicked, close the custom dialog
                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Task Cancelled", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.setView(promtView);
                dialog.show();
                return;

            default:
                return;
        }
    }



    public void onDoneButtonClick(View view) {
        ToDoItem m = (ToDoItem) view.getTag();
        db.deleteItem(m.getId());
        Set_Referash_Data();
        results.add(m);
        completedDb = new ToDoItemDatabase(getApplicationContext(), COMPLETED_TODO);
        completedDb.insertIntoTable(m);
//        completedDb.close();

    }

    public static List<ToDoItem> getTodoItemsList() {
        return results;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        final ToDoItem r = (ToDoItem) listView.getItemAtPosition(arg2);

        LayoutInflater inflater = LayoutInflater.from(this);
        View promtView = inflater.inflate(R.layout.custom_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();

        final EditText title = (EditText) promtView.findViewById(R.id.editTextTitle);
        final EditText description = (EditText) promtView.findViewById(R.id.editTextDescription);
        final DatePicker datePicker = (DatePicker) promtView.findViewById(R.id.datePicker);
        title.setText(r.getTitle());
        description.setText(r.getDescription());
        String updateDate[] = r.getDate().split("/");
        Toast.makeText(MainActivity.this, r.getDate(), Toast.LENGTH_SHORT).show();
        datePicker.updateDate(Integer.parseInt(updateDate[0]), Integer.parseInt(updateDate[1]) - 1, Integer.parseInt(updateDate[2]));
        Button dialogButtonSave = (Button) promtView.findViewById(R.id.buttonSave);
        dialogButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (title.getText().toString().equals("") || description.getText().toString().equals("")) {
                        String str = "Don't Leave any field blank !";
                        Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        String titleInput = title.getText().toString();
                        String descriptionInput = description.getText().toString();
                        Toast toast = Toast.makeText(MainActivity.this, titleInput, Toast.LENGTH_SHORT);
                        day = datePicker.getDayOfMonth();
                        month = datePicker.getMonth() + 1;
                        year = datePicker.getYear();
                        String date = year + "/" + month + "/" + day;
                        r.setTitle(titleInput);
                        r.setDescription(descriptionInput);
                        r.setDate(date);
//                        db = new TaskDBHelper(MainActivity.this);
                        db = new ToDoItemDatabase(getApplicationContext(), TODO);
//                        db = new ToDoItemDatabase(MainActivity.this, TODO);
                        db.updateTask(r);
                        db.close();
                        Set_Referash_Data();
                        dialog.dismiss();
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace());
                }
            }
        });
        Button dialogButtonCancel = (Button) promtView.findViewById(R.id.buttonCancel);
        // if button is clicked, close the custom dialog
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Task Cancelled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setView(promtView);
        dialog.show();
    }

}
