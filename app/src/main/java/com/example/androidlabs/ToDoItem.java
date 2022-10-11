package com.example.androidlabs;

public class ToDoItem {
    private String toDo = "";
    private Boolean isUrgent = false;

    public ToDoItem(){
    }

    public ToDoItem(String toDo, Boolean isUrgent){
        this.toDo = toDo;
        this.isUrgent = isUrgent;
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
}
