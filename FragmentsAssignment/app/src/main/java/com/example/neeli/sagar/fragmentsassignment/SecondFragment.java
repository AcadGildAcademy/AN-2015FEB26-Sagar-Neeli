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
 * Created by sneeli on 4/17/2015.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{
    Button click2;
    TextView txt2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.frag_two, container, false);
        return view2;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txt2 = (TextView) getActivity().findViewById(R.id.textViewFrag2);
        click2 = (Button) getActivity().findViewById(R.id.buttonFrag2);
        click2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        txt2.setText("This is fragment two");
    }
}
