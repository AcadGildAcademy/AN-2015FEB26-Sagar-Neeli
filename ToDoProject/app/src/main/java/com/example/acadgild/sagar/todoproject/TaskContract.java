package com.example.acadgild.sagar.todoproject;

import android.provider.BaseColumns;

/**
 * Created by sneeli on 4/12/2015.
 */
public class TaskContract {

    public static final String DB_NAME = "com.example.acadgild.sagar.todoproject.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String _ID = BaseColumns._ID;
    }

}
