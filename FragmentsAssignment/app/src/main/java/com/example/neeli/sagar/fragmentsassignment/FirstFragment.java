package com.example.neeli.sagar.fragmentsassignment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sneeli on 4/17/2015.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{

    Button click1;
    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txt = (TextView) getActivity().findViewById(R.id.textViewFrag1);
        click1 = (Button) getActivity().findViewById(R.id.buttonFrag1);
        click1.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonFrag1)
            txt.setText("This is fragment one");
    }
}
