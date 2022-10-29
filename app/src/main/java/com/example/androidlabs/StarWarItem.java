package com.example.androidlabs;

public class StarWarItem {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = "";
    private int height = 0;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    private int mass = 0;

    public StarWarItem(){
    }

    public StarWarItem(String name, int height, int mass){
        this.name = name;
        this.height = height;
        this.mass = mass;
    }
}
