package com.example.acadgild.sagar.todoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneeli on 4/12/2015.
 */
public class TaskDBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "com.example.acadgild.sagar.todoproject.TASKS";
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String TASK_TITLE = "title";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASH_DATE = "date";
    public static final String _ID = BaseColumns._ID;

    SQLiteDatabase db;

    public TaskDBHelper(Context context) {
        super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_TITLE + " TEXT, " + TASK_DESCRIPTION + " TEXT, " + TASH_DATE + " TEXT);";

        Log.d("TaskDBHelper", "Query to form table: " + sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertIntoTable(String title, String description, String date)
    {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.clear();
        values.put(TASK_TITLE, title);
        values.put(TASK_DESCRIPTION, description);
        values.put(TASH_DATE, date);

//        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<ToDoItem> getAllStudents(){
        List<ToDoItem> todo = new ArrayList<ToDoItem>();
        db = this.getReadableDatabase();
        String all = "Select * from " + TABLE_NAME;
        Cursor cur = db.rawQuery(all, null);

        if(cur.moveToFirst())
        {
            do {
                ToDoItem item = new ToDoItem();
                item.setId(cur.getInt(cur.getColumnIndex(_ID)));
                item.setTitle(cur.getString(cur.getColumnIndex(TASK_TITLE)));
                item.setDescription(cur.getString(cur.getColumnIndex(TASK_DESCRIPTION)));
                item.setDate(cur.getString(cur.getColumnIndex(TASH_DATE)));
                todo.add(item);
            } while(cur.moveToNext());

        }
        return todo;

    }
}
