package com.example.salonappointmentsystem;

import java.util.UUID;

public class servicesTable {

    private String serviceName;
    private String servicePrice;
    private String duration;
    String service_ID;
    String unique;

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

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

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getService_ID() {
        return service_ID;
    }


}
