package com.example.androidlabs;

public class ToDoItem {
    private Integer id = -1;
    private String toDo = "";
    private Boolean isUrgent = false;

    public ToDoItem(){
    }

    public ToDoItem(Integer id, String toDo, Boolean isUrgent ){
        this.id = id;
        this.toDo = toDo;
        this.isUrgent = isUrgent;
    }

    public ToDoItem(Integer id, String toDo, Integer urgent ){
        this.id = id;
        this.toDo = toDo;
        this.isUrgent = urgent != 0;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public Boolean getUrgent() {
        return isUrgent;
    }

    public void setUrgent(Boolean urgent) {
        isUrgent = urgent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
