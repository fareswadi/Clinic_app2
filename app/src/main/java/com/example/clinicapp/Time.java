package com.example.clinicapp;

import java.io.Serializable;

public class Time implements Serializable {
    private int id;
    private String time;

    public Time() {

    }

    public Time( String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
