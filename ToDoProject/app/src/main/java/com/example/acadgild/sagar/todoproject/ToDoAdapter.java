package com.example.acadgild.sagar.todoproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sneeli on 4/13/2015.
 */
public class ToDoAdapter extends BaseAdapter{

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public ToDoAdapter(Context context, int todotask, Cursor cursor, String[] data, int[] ints) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.custom_list, null);
        TextView title = (TextView) vi.findViewById(R.id.textViewTitle);
        TextView description = (TextView) vi.findViewById(R.id.textViewDescription);
        TextView date = (TextView) vi.findViewById(R.id.textViewDate);
        title.setText(data[position]);
        description.setText(data[position]);
        date.setText(data[position]);
        return vi;
    }
}
