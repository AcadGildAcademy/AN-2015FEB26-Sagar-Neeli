package com.example.neeli.sagar.todoapp;

import android.app.Activity;
import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ToDoItem> items;
    ToDoItemDatabase db;
    MainActivity main;
    public ActionMode mActionMode;

    public CustomListAdapter(Activity activity, List<ToDoItem> items) {
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
//        Button button;
        ImageView button;
        ToDoItem m;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
//        ToDoItem m = items.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null)
//            convertView = inflater.inflate(R.layout.custom_list, null);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.m = items.get(position);
            holder.title = (TextView) convertView.findViewById(R.id.textViewTitle);
//            holder.title.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
//                @Override
//                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                    mActionMode = mode;
//                    // Can now use the mode whenever (if it's not null)
//                    // e.g. call mActionMode.finish()
//                    return true; // true = create the ActionMode
//                }
//
//                @Override
//                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                    return false;
//                }
//
//                @Override
//                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                    return false;
//                }
//
//                @Override
//                public void onDestroyActionMode(ActionMode mode) {
//                    mActionMode = null;
//                }
//            });
            holder.description = (TextView) convertView.findViewById(R.id.textViewDescription);
            holder.date = (TextView) convertView.findViewById(R.id.textViewDate);
//            holder.button = (Button)convertView.findViewById(R.id.completeBtn);
            holder.button = (ImageView)convertView.findViewById(R.id.completeBtn);
            holder.button.setTag(holder.m);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
;

//        ViewHolder holder = (ViewHolder) convertView.getTag();
//        TextView title = (TextView) convertView.findViewById(R.id.textViewTitle);
//        TextView description = (TextView) convertView.findViewById(R.id.textViewDescription);
//        TextView date = (TextView) convertView.findViewById(R.id.textViewDate);
//        Button button = (Button) convertView.findViewById(R.id.completeBtn);

//        title.setText(m.getTitle());
//        description.setText(m.getDescription());
//        date.setText(m.getDate());
        holder.title.setText(holder.m.getTitle());
        holder.description.setText(holder.m.getDescription());
        holder.date.setText(holder.m.getDate());
//        holder.button.setOnClickListener((View.OnClickListener) this);

//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(getView(view.getContext().getApplicationContext(), "Delte", Toast.LENGTH_LONG).show();
////                view.getTag();
//                View v = (View) view.getTag();
////                Intent i = new Intent(view.getContext(), DetailedResponseActivity.class);
////                view.getContext().startActivity(i);
//
////                TextView title = (TextView) v.findViewById(R.id.textViewTitle);
////                TextView description = (TextView) v.findViewById(R.id.textViewDescription);
////                TextView date = (TextView) v.findViewById(R.id.textViewDate);
//                db.deleteItem(v.getId());
//
////                main.Set_Referash_Data();
//            }
//        });
        return convertView;
    }
}