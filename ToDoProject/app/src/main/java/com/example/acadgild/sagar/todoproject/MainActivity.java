package com.example.acadgild.sagar.todoproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Toolbar toolbar;
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home", "Events", "Mail", "Shop", "Travel"};
    int ICONS[] = {R.drawable.ic_home, R.drawable.ic_events, R.drawable.ic_mail, R.drawable.ic_shop, R.drawable.ic_travel};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Sagar Neeli";
    String EMAIL = "sagarneeli1191@gmail.com";
    int PROFILE = R.drawable.aka;

    RecyclerView mRecyclerView, recyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter, adapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    private EditText text_title, text_description;
    private TextView text_date;
    private DatePicker date_picker;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 100;

    final Context context = this;
    Button customBtn;

//    private ToDoDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

//        drawerFragment = (NavigationAppDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout, toolbar));
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_new);
//        icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_float));
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        ImageView editFab = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_alphabets);

        ImageView messageFab = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_calendar);

        ImageView locationFab = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_important);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));
//        itemBuilder.setBackgroundDrawable(R.drawable.selec);
        SubActionButton editFABbutton = itemBuilder.setContentView(editFab).build();
        SubActionButton messageFABbutton = itemBuilder.setContentView(messageFab).build();
        SubActionButton locationFABbutton = itemBuilder.setContentView(locationFab).build();

//        editFABbutton.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(editFABbutton)
                .addSubActionView(messageFABbutton)
                .addSubActionView(locationFABbutton)
                .attachTo(actionButton)
                .build();


        customBtn = (Button) findViewById(R.id.button);
        customBtn.setOnClickListener(this);

//        datasource = new ToDoDataSource(this);
//        List<ToDoItem> notes = datasource.findAll();
//        ToDoItem note = notes.get(0);
//        note.setTitle("Updated!");
//        datasource.update(note);
//
//        notes = datasource.findAll();
//        note = notes.get(0);

//        Log.i("NOTES", note.getKey() + ": " + note.getTitle());

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

        if (id == R.id.navigate) {
            Intent i = new Intent(this, SubActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

//        final Dialog dialog = new Dialog(context, R.style.myBackgroundStyle);
        final Dialog dialog = new Dialog(context);
//        final Dialog dialog = new Dialog(this, R.style.NewDialog);
//        MaterialDialog dialog1 = new MaterialDialog(context).setView(R.layout.custom_dialog).setTitle("Enter an Activity");
//        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Base));
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Enter an activity");

//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button dialogButton = (Button) dialog.findViewById(R.id.buttonSave);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
