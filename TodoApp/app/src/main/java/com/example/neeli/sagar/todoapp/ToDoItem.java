package com.example.neeli.sagar.todoapp;

/**
 * Created by sneeli on 4/11/2015.
 */
public class ToDoItem {

    private int id;
    private String title;
    private String description;
    private String date;

    public ToDoItem(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public ToDoItem() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public static ToDoItem getNew() {
//
//        Locale locale = new Locale("en-US");
//        Locale.setDefault(locale);
//
//        String pattern = "MM/dd/yyyy";
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        String key = formatter.format(new Date());
//
//        ToDoItem note = new ToDoItem();
//        note.setKey(key);
////        note.setDescription();
//        note.setTitle("");
//        return note;
//    }


}
