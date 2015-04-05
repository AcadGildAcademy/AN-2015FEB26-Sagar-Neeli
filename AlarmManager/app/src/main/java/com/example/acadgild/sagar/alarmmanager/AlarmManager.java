package com.example.acadgild.sagar.alarmmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class AlarmManager extends ActionBarActivity implements View.OnClickListener{

//    DatePicker datePicker;
    TimePicker timePicker;
    Calendar calendar;
    EditText date, time;
    ImageView imgDate, imgTime;
    Button add;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;
    int year, month, day;
    int hour;
    int minute;

    static final int DATE_DIALOG_ID = 998;
    static final int TIME_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        // get the id's
        date = (EditText) findViewById(R.id.editText);
        time = (EditText) findViewById(R.id.editText2);
        imgDate = (ImageView) findViewById(R.id.imageViewDate);
        imgTime = (ImageView) findViewById(R.id.imageViewTime);
        add = (Button) findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.listDateTime);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        listItems = new ArrayList<String>();
//        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1, listItems);
        adapter = new ArrayAdapter<String>(AlarmManager.this,R.layout.custom_alarm_textview,R.id.text,listItems);
        listView.setAdapter(adapter);
        add.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(998);
    }

    @SuppressWarnings("deprecation")
    public void showTimePickerDialog(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, myDateListener, year, month, day);
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    // set current time into textview
                   // updateTime(hour,minute);

                    String am_pm = "";
                    if(selectedHour > 12) {
                        hour -= 12;
                        am_pm = "PM";
                    } else if (selectedHour == 0){
                        hour += 12;
                        am_pm = "AM";
                    } else if (selectedHour == 12) {
                        am_pm = "PM";
                    } else {
                        am_pm = "AM";
                    }
//                    timePicker.setIs24HourView(false);
                    time.setText(new StringBuilder().append(hour).append(":").append(pad(minute)).append(" " + am_pm));
                }
            };

    private void showDate(int year, int i, int day) {
        date.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }

    // Used to convert 24hr format to 12hr format with AM/PM values
    private void updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        time.setText(aTime);
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                try{
                    if(date.getText().toString().equals("") || time.getText().toString().equals("")){
                        String str="Don't Leave any field blank !";
                        Toast toast = Toast.makeText(AlarmManager.this, str, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else {
                        String new_item = "Date : " + date.getText().toString() + ", Time : " + time.getText().toString();
                        Toast.makeText(getApplicationContext(), new_item, Toast.LENGTH_SHORT).show();
                        if (!listItems.contains(new_item))
                            listItems.add(new_item);
                        else
                            Toast.makeText(getApplicationContext(), "Set new time and date", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getStackTrace());
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_manager, menu);
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
