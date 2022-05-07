package com.example.salonappointmentsystem;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalTime;

public class salon implements Parcelable {

    private String nameOfOwner;
    private Integer phone;
    private String password;


    private String nameOfSalon;
    private String location;
    private String description;

    private String day;
    private LocalTime time;

    public salon() {
    }

    protected salon(Parcel in) {
        nameOfOwner = in.readString();
        if (in.readByte() == 0) {
            phone = null;
        } else {
            phone = in.readInt();
        }
        password = in.readString();
        nameOfSalon = in.readString();
        location = in.readString();
        description = in.readString();
        day = in.readString();
    }

    public static final Creator<salon> CREATOR = new Creator<salon>() {
        @Override
        public salon createFromParcel(Parcel in) {
            return new salon(in);
        }

        @Override
        public salon[] newArray(int size) {
            return new salon[size];
        }
    };

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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameOfOwner);
        if (phone == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(phone);
        }
        parcel.writeString(password);
        parcel.writeString(nameOfSalon);
        parcel.writeString(location);
        parcel.writeString(description);
        parcel.writeString(day);
    }
}
