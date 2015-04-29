package com.example.neeli.sagar.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneeli on 4/21/2015.
 */
public class ToDoItemDatabase extends SQLiteOpenHelper{

    public static final String DB_NAME = "com.example.acadgild.sagar.todoproject.TASKS.db";
    public static String TABLE_NAME = "TODO";
    public static String TASK_TITLE = "title";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASK_DATE = "date";
    public static final String _ID = "_id";
    public static final int DB_VERSION = 1;

    private static String DB_CREATE_SCRIPT;
    private static String DB_DELETE_SCRIPT;
    private final List<ToDoItem> todo = new ArrayList<ToDoItem>();



    public ToDoItemDatabase(Context context, String table_name) {
        super(context, DB_NAME, null, DB_VERSION);
        TABLE_NAME = table_name;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        DB_CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_TITLE + " TEXT, " + TASK_DESCRIPTION + " TEXT, " + TASK_DATE + " TEXT);";
        db.execSQL(DB_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DB_DELETE_SCRIPT = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(DB_DELETE_SCRIPT);
        onCreate(db);
    }

    public void insertIntoTable(ToDoItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, item.getTitle());
        values.put(TASK_DESCRIPTION, item.getDescription());
        values.put(TASK_DATE, item.getDate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    ToDoItem Get_ToDoItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { _ID,
                        TASK_TITLE, TASK_DESCRIPTION, TASK_DATE }, _ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDoItem contact = new ToDoItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        db.close();

        return contact;
    }

    public List<ToDoItem> getAllTasks(){
        try {
            todo.clear();
            SQLiteDatabase db = this.getReadableDatabase();
//            String all = "Select * from " + TABLE_NAME;
            String all = "Select * from " + TABLE_NAME + " order by " + TASK_DATE + " ASC";
            Cursor cur = db.rawQuery(all, null);

            if(cur.moveToFirst())
            {
                do {
                    ToDoItem item = new ToDoItem();
                    item.setId(cur.getInt(cur.getColumnIndex(_ID)));
                    item.setTitle(cur.getString(cur.getColumnIndex(TASK_TITLE)));
                    item.setDescription(cur.getString(cur.getColumnIndex(TASK_DESCRIPTION)));
                    item.setDate(cur.getString(cur.getColumnIndex(TASK_DATE)));
                    todo.add(item);
                } while(cur.moveToNext());
            }
            cur.close();
            db.close();
            return todo;
        } catch (Exception e) {
            Log.e("All items", "" + e);
        }
        return todo;
    }

    public void updateTask(ToDoItem task) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, task.getTitle());
        values.put(TASK_DESCRIPTION, task.getDescription());
        values.put(TASK_DATE, task.getDate());
        db.update(TABLE_NAME, values, _ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

}
