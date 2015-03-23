package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sneeli on 3/22/2015.
 */
public class HideAndSeek extends Activity implements View.OnClickListener{

    boolean HideSeek = true;
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hide_and_seek);
        btn = (Button) findViewById(R.id.buttonHideSeek);
        txt = (TextView) findViewById(R.id.textData);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(HideSeek) {
            txt.setVisibility(View.INVISIBLE);
            btn.setText("SEEK");
            HideSeek = false;
        }
        else {
            txt.setVisibility(View.VISIBLE);
            btn.setText("HIDE");
            HideSeek = true;
        }
    }
}
