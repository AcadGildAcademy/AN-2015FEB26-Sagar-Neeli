//package com.example.acadgild.sagar.todoproject;
//
//import android.support.v7.widget.RecyclerView;
//import android.text.format.DateUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by sneeli on 4/9/2015.
// */
//public class RecyclerViewAdapter extends
//        RecyclerView.Adapter
//                <RecyclerViewAdapter.ListItemViewHolder> {
//
//    private List<MainActivity> items;
//
//    RecyclerViewAdapter(List<MainActivity> modelData) {
//        if (modelData == null) {
//            throw new IllegalArgumentException(
//                    "modelData must not be null");
//        }
//        this.items = modelData;
//    }
//
//
//    @Override
//    public void onBindViewHolder(
//            ListItemViewHolder viewHolder, int position) {
//        MainActivity model = items.get(position);
//        viewHolder.label.setText(model.label);
//        String dateStr = DateUtils.formatDateTime(
//                viewHolder.label.getContext(),
//                model.dateTime.getTime(),
//                DateUtils.FORMAT_ABBREV_ALL);
//        viewHolder.dateTime.setText(dateStr);
//    }
//
//    @Override
//    public RecyclerViewAdapter.ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public final static class ListItemViewHolder
//            extends RecyclerView.ViewHolder {
//        TextView label;
//        TextView dateTime;
//
//        public ListItemViewHolder(View itemView) {
//            super(itemView);
//            label = (TextView) itemView.findViewById(R.id.txt_label_item);
//            dateTime = (TextView) itemView.findViewById(R.id.txt_date_time);
//        }
//    }
//}