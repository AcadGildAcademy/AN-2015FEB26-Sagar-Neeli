package com.example.neeli.sagar.todoapp;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_events,R.drawable.ic_mail,R.drawable.ic_shop,R.drawable.ic_travel};
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

    private ListAdapter todoListAdapter;
    private TaskDBHelper todoListSQLHelper;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.NavigationRecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.drawer_open,R.string.drawer_close){

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


        listView = (ListView) findViewById(android.R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(this);
        updateToDoList();

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
        switch (view.getId()) {
            case R.id.fab:
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Enter an activity");
                final EditText title = (EditText) dialog.findViewById(R.id.editTextTitle);
                final EditText description = (EditText) dialog.findViewById(R.id.editTextDescription);
                final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);
                Button dialogButtonSave = (Button) dialog.findViewById(R.id.buttonSave);
                dialogButtonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(title.getText().toString().equals("") || description.getText().toString().equals("")){
                                Toast.makeText(MainActivity.this, "Activity Saved", Toast.LENGTH_SHORT).show();
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
                                String date = day + "/" + month + "/" + year;
                                todoListSQLHelper.insertIntoTable(titleInput, descriptionInput, date);
                                todoListSQLHelper = new TaskDBHelper(MainActivity.this);
                                updateToDoList();
                                dialog.dismiss();
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getStackTrace());
                        }
                    }
                });

                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
                // if button is clicked, close the custom dialog
                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Task Cancelled", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return;

            default:
                return;
        }
    }


    private void updateToDoList() {
        todoListSQLHelper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TaskDBHelper.TABLE_NAME,
                new String[]{TaskDBHelper._ID, TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASK_DATE},
                null, null, null, null, TaskDBHelper.TASK_DATE + " ASC");

        //binds the todo task list with the UI
        todoListAdapter = new SimpleCursorAdapter(
                this,
                R.layout.custom_list,
                cursor,
                new String[]{TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASK_DATE},
                new int[]{R.id.textViewTitle, R.id.textViewDescription, R.id.textViewDate},
                0
        );

        setListAdapter(todoListAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.todoTaskTV);
        String todoTaskItem = todoTV.getText().toString();

        String deleteTodoItemSql = "DELETE FROM " + TaskDBHelper.TABLE_NAME +
                " WHERE " + TaskDBHelper.TASK_TITLE + " = '" + todoTaskItem + "'";

        todoListSQLHelper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateToDoList();
    }


    protected ListView getListView() {
        if (listView == null) {
            listView = (ListView) findViewById(android.R.id.list);
        }
        return listView;
    }

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }

}
