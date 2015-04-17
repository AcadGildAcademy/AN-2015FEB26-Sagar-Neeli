package com.example.neeli.sagar.databaseassignment;

import android.graphics.Bitmap;

/**
 * Created by sneeli on 4/17/2015.
 */
public class Employee {

    public String name;
    public int age;
    public Bitmap photo;

    public Employee(String name, int age, Bitmap photo) {
        this.name = name;
        this.age = age;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
