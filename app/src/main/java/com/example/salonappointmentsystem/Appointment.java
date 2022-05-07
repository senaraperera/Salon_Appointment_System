package com.example.salonappointmentsystem;

public class Appointment {
    private String appID;
    private String salonID;
    private String serviceID;
    private String cusID;
    private String AppDate;
    private String amount;

    public Appointment() {
    }

    public Appointment(String appID, String salonID, String serviceID, String cusID, String appDate, String amount) {
        this.appID = appID;
        this.salonID = salonID;
        this.serviceID = serviceID;
        this.cusID = cusID;
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

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getAppDate() {
        return AppDate;
    }

    public void setAppDate(String appDate) {
        AppDate = appDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
