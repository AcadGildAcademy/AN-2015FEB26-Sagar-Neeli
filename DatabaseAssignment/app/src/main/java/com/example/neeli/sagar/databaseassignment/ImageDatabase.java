package com.example.neeli.sagar.databaseassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sneeli on 4/17/2015.
 */
public class ImageDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "EMPLOYEESDB.db";
    public static final int DB_VERSION = 1;
    private static final String DB_TABLE_NAME = "EMPLOYEE";
    public static final String EMPLOYEE_ID = "_ID";
    public static final String EMPLOYEE_NAME = "NAME";
    public static final String EMPLOYEE_AGE = "AGE";
    public static final String EMPLOYEE_PHOTO = "PHOTO";

    private  static final String DB_CREATE_SCRIPT = "CREATE TABLE " + DB_TABLE_NAME + " (" + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EMPLOYEE_NAME + " TEXT NOT NULL UNIQUE, " + EMPLOYEE_AGE + " INTEGER, " + EMPLOYEE_PHOTO + " BLOB NOT NULL);";
    private static final String DB_DELETE_SCRIPT = "DROP TABLE IF EXISTS "+ DB_TABLE_NAME;

    private SQLiteDatabase sqLiteDatabase = null;

    public ImageDatabase(Context context) {
        super(context, DB_TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        sqLiteDatabase.execSQL(DB_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(DB_DELETE_SCRIPT);
        onCreate(sqLiteDatabase);
    }

    public void openDB() {
        if (this.sqLiteDatabase == null)
            this.sqLiteDatabase = this.getWritableDatabase();
    }

//    public ImageDatabase openDB() throws SQLException {
//        this.sqLiteDatabase = this.getWritableDatabase();
//        return this;
//    }

    public void closeDB() {
        if (this.sqLiteDatabase != null) {
            if (this.sqLiteDatabase.isOpen())
                this.sqLiteDatabase.close();
        }
    }

    public void insertEmployeeDetails(Employee employee) {
        if (this.sqLiteDatabase == null) {
            this.sqLiteDatabase = this.getWritableDatabase();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.getName());
        contentValues.put(EMPLOYEE_AGE, employee.getAge());
        contentValues.put(EMPLOYEE_PHOTO, Utility.getBytes(employee.getPhoto()));
        this.sqLiteDatabase.insert(DB_TABLE_NAME, null, contentValues);
    }

    public Employee retrieveEmployeeDetails() {

        Cursor cur = this.sqLiteDatabase.query(true, DB_TABLE_NAME, new String[] {EMPLOYEE_NAME, EMPLOYEE_AGE, EMPLOYEE_PHOTO},
                null, null, null, null, null, null);
        if (cur.moveToFirst()) {
            String name = cur.getString(cur.getColumnIndex(EMPLOYEE_NAME));
            int age = cur.getInt(cur.getColumnIndex(EMPLOYEE_AGE));
            byte[] photo = cur.getBlob(cur.getColumnIndex(EMPLOYEE_PHOTO));
            cur.close();
            return new Employee(name, age, Utility.getPhoto(photo));
        }
        cur.close();
        return null;
    }
}
