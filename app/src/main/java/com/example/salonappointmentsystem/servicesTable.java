package com.example.salonappointmentsystem;

import android.net.Uri;
import android.widget.ImageView;

import java.util.UUID;

public class servicesTable {

    private String serviceName;
    private String servicePrice;
    private String duration;
    private String image;
    String service_ID;
    String unique;

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public servicesTable() { }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
