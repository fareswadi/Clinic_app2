package com.example.clinicapp;

import java.io.Serializable;

public class Doctor implements Serializable{
    private int ID;
    private String firstName;
    private String lastName;
    private String Specialization;
    private String Email;
    private String phone;
    private String gender;
    private int img;

    public Doctor(String firstName, String lastName, int img) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.img = img;
    }

    public Doctor(String firstName, String lastName, String specialization, String phone, int img) {
        this.firstName = firstName;
        this.lastName = lastName;
        Specialization = specialization;
        this.phone = phone;
        this.img = img;
    }

    public Doctor(String firstName, String lastName, String specialization, String email, String phone, String gender) {

        this.firstName = firstName;
        this.lastName = lastName;
        Specialization = specialization;
        Email = email;
        this.phone = phone;

        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
