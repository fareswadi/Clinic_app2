package com.example.clinicapp;

import java.io.Serializable;

public class BookingAppointment implements Serializable {
    private int ID;
    private String doctorName;
    private String clinicName;
    private String date;
    private String time;

    public BookingAppointment(String doctorName, String clinicName, String date, String time) {
        this.doctorName = doctorName;
        this.clinicName = clinicName;
        this.date = date;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
