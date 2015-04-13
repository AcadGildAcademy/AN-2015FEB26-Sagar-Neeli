package com.example.acadgild.sagar.todoproject;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by sneeli on 4/12/2015.
 */
public class ToDoActivity extends ListActivity {

    private ListAdapter todoListAdapter;
    private TaskDBHelper todoListSQLHelper;
    EditText description;
    Calendar calendar;
    int day;
    int month;
    int year;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getListView().setFastScrollEnabled(true);
        setContentView(R.layout.activity_todo);
//        LayoutInflater li = LayoutInflater.from(this);
//        final View v = li.inflate(R.layout.custom_dialog, null);
//        title = (EditText) findViewById(R.id.editTextTitle);
//        description = (EditText) findViewById(R.id.editTextDescription);
//        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
//        calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
        updateToDoList();
//        setListAdapter(new SimpleCursorAdapter(this, todoListSQLHelper.getAllStudents()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final Dialog dialog = new Dialog(ToDoActivity.this);
//                LayoutInflater inflater = LayoutInflater.from(this);
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("New Location");
//                builder.setView(inflater.inflate(R.layout.custom_dialog, null));

                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Enter an activity");

                final EditText title = (EditText) dialog.findViewById(R.id.editTextTitle);
                final EditText description = (EditText) dialog.findViewById(R.id.editTextDescription);
                final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);
                Button dialogButtonSave = (Button) dialog.findViewById(R.id.buttonSave);
                // if button is clicked, close the custom dialog
                dialogButtonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(ToDoActivity.this, "Activity Saved", Toast.LENGTH_SHORT).show();
                        try{
                            if(title.getText().toString().equals("") || description.getText().toString().equals("")){
                                Toast.makeText(ToDoActivity.this, "Activity Saved", Toast.LENGTH_SHORT).show();
                                String str = "Don't Leave any field blank !";
                                Toast toast = Toast.makeText(ToDoActivity.this, str, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            } else {
                                String titleInput = title.getText().toString();
                                String descriptionInput = description.getText().toString();
                                Toast toast = Toast.makeText(ToDoActivity.this, titleInput, Toast.LENGTH_SHORT);
                                day = datePicker.getDayOfMonth();
                                month = datePicker.getMonth() + 1;
                                year = datePicker.getYear();
//                        String date = new StringBuilder().append(day)
//                                .append("/").append(month).append("/").append(year).append(" ").toString();
                                String date = day + "/" + month + "/" + year;
//                        String date = new StringBuilder().append(datePicker.getDayOfMonth())
//                                .append("/").append(datePicker.getMonth() + 1).append("/").append(datePicker.getYear()).append(" ").toString();
                                todoListSQLHelper.insertIntoTable(titleInput, descriptionInput, date);
                                todoListSQLHelper = new TaskDBHelper(ToDoActivity.this);
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
                        Toast.makeText(ToDoActivity.this, title.getText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;

            default:
                return false;
        }

    }

    private void updateToDoList() {
        todoListSQLHelper = new TaskDBHelper(ToDoActivity.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TaskDBHelper.TABLE_NAME,
                new String[]{TaskDBHelper._ID, TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASH_DATE},
                null, null, null, null, null);

        //binds the todo task list with the UI
        todoListAdapter = new SimpleCursorAdapter(
                this,
                R.layout.custom_list,
                cursor,
                new String[]{TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASH_DATE},
                new int[]{R.id.textViewTitle, R.id.textViewDescription, R.id.textViewDate},
                0
        );

        this.setListAdapter(todoListAdapter);
//        listview = (ListView) findViewById(R.id.listViewCustom);
//        listview.setAdapter(new ToDoAdapter(this, R.layout.todotask, cursor, new String[] {TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASH_DATE}, new int[]{R.id.todoTaskTV}));
//        listview.setAdapter(new ToDoAdapter(this, R.layout.todotask, cursor,
//                new String[] {TaskDBHelper.TASK_TITLE, TaskDBHelper.TASK_DESCRIPTION, TaskDBHelper.TASH_DATE}, new int[]{R.id.todoTaskTV}));

    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.todoTaskTV);
        String todoTaskItem = todoTV.getText().toString();

        String deleteTodoItemSql = "DELETE FROM " + TaskDBHelper.TABLE_NAME +
                " WHERE " + TaskDBHelper.TASK_TITLE + " = '" + todoTaskItem + "'";

        todoListSQLHelper = new TaskDBHelper(ToDoActivity.this);
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateToDoList();
    }
}
