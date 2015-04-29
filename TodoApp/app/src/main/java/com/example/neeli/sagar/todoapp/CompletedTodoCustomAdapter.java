package com.example.neeli.sagar.todoapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CompletedTodoCustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ToDoItem> items;
    ToDoItemDatabase db;
    MainActivity main;

    public CompletedTodoCustomAdapter(Activity activity, List<ToDoItem> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        Button button;
        ToDoItem m;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_complete_list, null);
            holder = new ViewHolder();
            holder.m = items.get(position);
            holder.title = (TextView) convertView.findViewById(R.id.textViewTitleCompleted);
            holder.description = (TextView) convertView.findViewById(R.id.textViewDescriptionCompleted);
            holder.date = (TextView) convertView.findViewById(R.id.textViewDateCompleted);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(holder.m.getTitle());
        holder.description.setText(holder.m.getDescription());
        holder.date.setText(holder.m.getDate());
        return convertView;
    }
}