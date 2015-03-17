package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sneeli on 3/13/2015.
 */
public class Rainbow extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Creating Linear layout
        LinearLayout linearLayout = new LinearLayout(this);
        // Setting orientation
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        createTextView(linearLayout,linearLayoutParams,"Red");
        setContentView(linearLayout, linearLayoutParams);
    }

    private void createTextView(LinearLayout linearLayout, LinearLayout.LayoutParams linearLayoutParams, String color) {
        LinearLayout.LayoutParams linearLayoutParamsView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.12f);
        TextView txtColor = new TextView(this);
        txtColor.setText(R.string.Red);
        txtColor.setTextSize(getResources().getDimension(R.dimen.text_medium));
        txtColor.setTextColor(getResources().getColor(R.color.Black));
        txtColor.setBackgroundColor(getResources().getColor(R.color.Red));
        txtColor.setGravity(Gravity.CENTER);
        txtColor.setLayoutParams(linearLayoutParamsView);
        linearLayout.addView(txtColor);
    }
}
