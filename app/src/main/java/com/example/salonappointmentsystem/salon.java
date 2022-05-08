package com.example.salonappointmentsystem;
import android.os.Parcel;
import android.os.Parcelable;



public class salon  {

    private String nameOfOwner;
    private Integer phone;
    private String password;
    private String id;

    private String nameOfSalon;
    private String location;
    private String description;

    private String day;
    private String time;

    public salon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameOfSalon() {
        return nameOfSalon;
    }

    public void setNameOfSalon(String nameOfSalon) {
        this.nameOfSalon = nameOfSalon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
