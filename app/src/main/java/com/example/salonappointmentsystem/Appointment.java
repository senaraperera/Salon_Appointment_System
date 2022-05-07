package com.example.salonappointmentsystem;

public class Appointment {
    private String appID;
    private String salonID;
    private String serviceID;
    private String AppDate;
    private double amount;

    public Appointment() {
    }

    public Appointment(String appID, String salonID, String serviceID, String appDate, double amount) {
        this.appID = appID;
        this.salonID = salonID;
        this.serviceID = serviceID;
        AppDate = appDate;
        this.amount = amount;
    }


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getSalonID() {
        return salonID;
    }

    public void setSalonID(String salonID) {
        this.salonID = salonID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getAppDate() {
        return AppDate;
    }

    public void setAppDate(String appDate) {
        AppDate = appDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
