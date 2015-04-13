//package com.example.acadgild.sagar.todoproject;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
///**
// * Created by sneeli on 4/12/2015.
// */
//public class ToDoDataSource {
//
//    private static final String PREFKEY = "notes";
//    private SharedPreferences notePrefs;
//
//    public ToDoDataSource(Context context) {
//        notePrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
//    }
//
//    public List<ToDoItem> findAll() {
//
//        Map<String, ?> notesMap = notePrefs.getAll();
//
//        SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
//
//        List<ToDoItem> noteList = new ArrayList<ToDoItem>();
//        for (String key : keys) {
//            ToDoItem note = new ToDoItem();
////            note.setKey(key);
//            note.setTitle((String) notesMap.get(key));
//            noteList.add(note);
//        }
//
//        return noteList;
//    }
//
//    public boolean update(ToDoItem note) {
//
//        SharedPreferences.Editor editor = notePrefs.edit();
////        editor.putString(note.getKey(), note.getTitle());
//        editor.commit();
//        return true;
//    }
//
//    public boolean remove(ToDoItem note) {
//
//        if (notePrefs.contains(note.getKey())) {
//            SharedPreferences.Editor editor = notePrefs.edit();
//            editor.remove(note.getKey());
//            editor.commit();
//        }
//
//        return true;
//    }
//}
