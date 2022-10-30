package com.example.androidlabs;

public class StarWarObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    private String name = "";
    private String height = "0";
    private String mass = "0";

    public StarWarObject(){
    }

    public StarWarObject(String name, String height, String mass){
        this.name = name;
        this.height = height;
        this.mass = mass;
    }
}
