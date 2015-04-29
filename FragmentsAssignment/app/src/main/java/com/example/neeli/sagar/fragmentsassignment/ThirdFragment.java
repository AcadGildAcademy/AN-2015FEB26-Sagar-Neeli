package com.example.neeli.sagar.fragmentsassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sneeli on 4/28/2015.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{
    Button click3;
    TextView txt3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.frag_three, container, false);
        return view3;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txt3 = (TextView) getActivity().findViewById(R.id.textViewFrag3);
        click3 = (Button) getActivity().findViewById(R.id.buttonFrag3);
        click3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        txt3.setText("This is fragment three");
    }
}
