package com.example.salonappointmentsystem;

import java.util.UUID;

public class servicesTable {
    String service_ID;
    private String serviceName;
    private int servicePrice;
    private int duration;




    public servicesTable() { }




    public void setService_ID(){
        this.service_ID= UUID.randomUUID().toString();
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getService_ID() {
        return service_ID;
    }


}
