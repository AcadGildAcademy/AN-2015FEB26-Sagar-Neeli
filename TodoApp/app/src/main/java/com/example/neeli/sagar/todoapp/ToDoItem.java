package com.example.neeli.sagar.todoapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sneeli on 4/11/2015.
 */
public class ToDoItem implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String date;

    public ToDoItem(int id, String title, String description, String date) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public ToDoItem() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeInt(this.id);
    }
}
