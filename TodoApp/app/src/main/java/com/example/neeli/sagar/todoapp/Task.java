package com.example.neeli.sagar.todoapp;

/**
 * Created by sneeli on 4/13/2015.
 */
public class Task {
    private String taskTitle;
    private String taskDescription;
    private String taskDate;
    private int status;
    private int taskId;

    public Task()
    {
        this.taskTitle=null;
        this.status=0;
    }

    public Task(String taskName, int status) {
        super();
        this.taskTitle = taskName;
        this.status = status;
    }

    public int getId() {
        return taskId;
    }

    public void setId(int id) {
        this.taskId = id;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}