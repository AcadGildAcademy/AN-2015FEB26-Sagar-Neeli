package com.example.neeli.sagar.databaseassignment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sneeli on 4/17/2015.
 */
public class InsertandRetriveEmployeeData extends ActionBarActivity {

    private ImageDatabase imgDb;
    TextView empName, empAge;
    ImageView empPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_retrieve_employee_data);
        imgDb = new ImageDatabase(this);
        Employee employee_one = new Employee("Sagar", 24, BitmapFactory.decodeResource(getResources(), R.drawable.employee_one_photo));
        imgDb.openDB();
        imgDb.insertEmployeeDetails(employee_one);
//        imgDb.close();
        employee_one = null;
        imgDb.openDB();
        employee_one = imgDb.retrieveEmployeeDetails();
        imgDb.close();
        empName = (TextView) findViewById(R.id.name);
        empAge = (TextView) findViewById(R.id.age);
        empPhoto = (ImageView) findViewById(R.id.photo);
        empName.setText(employee_one.getName());
        empAge.setText("" + employee_one.getAge());
        empPhoto.setImageBitmap(employee_one.getPhoto());
    }
}
