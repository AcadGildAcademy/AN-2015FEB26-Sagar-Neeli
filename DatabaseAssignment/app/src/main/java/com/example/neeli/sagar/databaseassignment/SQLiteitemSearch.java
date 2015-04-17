package com.example.neeli.sagar.databaseassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sneeli on 4/16/2015.
 */
public class SQLiteitemSearch extends SQLiteOpenHelper{

    private static final String DB_NAME = "Products.db";
    private static final int DB_VERSION_NUMBER = 1;
    private static final String DB_TABLE_NAME = "itemsearch";
    private static final String DB_COLUMN_1_NAME = "item_name";
//    public static final String _ID = BaseColumns._ID;

    private static final String DB_CREATE_SCRIPT = "create table "
            + DB_TABLE_NAME + " (_id integer primary key autoincrement, item_name text not null);)";

    private SQLiteDatabase sqliteDBInstance;


    public SQLiteitemSearch(Context context) {
//        sqliteDBInstance = getWritableDatabase();
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("onCreate", "Creating the database...");
        this.sqliteDBInstance = sqLiteDatabase;
        sqliteDBInstance.execSQL(DB_CREATE_SCRIPT);
    }

        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_NAME);
            onCreate(sqLiteDatabase);
    }

    public void openDB() {
        Log.i("openDB", "Checking sqliteDBInstance...");
        if (this.sqliteDBInstance == null) {
            Log.i("openDB", "Creating sqliteDBInstance...");
            this.sqliteDBInstance = this.getWritableDatabase();
        }

    }

    public void closeDB() {
        if (this.sqliteDBInstance != null) {
            if (this.sqliteDBInstance.isOpen())
                this.sqliteDBInstance.close();
        }
    }

    public long insertItemSearch(String ItemBrandName) {
        Log.i("openDB", "Checking sqliteDBInstance...");
        if (this.sqliteDBInstance == null) {
            Log.i("openDB", "Creating sqliteDBInstance...");
            this.sqliteDBInstance = this.getWritableDatabase();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_1_NAME, ItemBrandName);
        Log.i(this.toString() + " - insertitmSearch", "Inserting: "+ ItemBrandName);
        return this.sqliteDBInstance.insert(DB_TABLE_NAME, null, contentValues);

    }

    public boolean removeitemSearch(String ItemBrandName) {
        int result = this.sqliteDBInstance.delete(DB_TABLE_NAME, "item_name='"
                + ItemBrandName + "'", null);

        if (result > 0)
            return true;
        else
            return false;
    }

    public long updateitmSearch(String oldItemBrandName, String newItemBrandName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_1_NAME, newItemBrandName);
        return this.sqliteDBInstance.update(DB_TABLE_NAME, contentValues,
                "item_name='" + oldItemBrandName + "'", null);
    }


    public String[] getAllItemFilter() {

        Cursor cursor = this.sqliteDBInstance
                .query(DB_TABLE_NAME, new String[]{DB_COLUMN_1_NAME}, null,
                        null, null, null, null);

        if (cursor.getCount() > 0) {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                str[i] = cursor.getString(cursor
                        .getColumnIndex(DB_COLUMN_1_NAME));
                i++;
            }
            return str;
        } else {
            return new String[] {};
        }
    }

}
