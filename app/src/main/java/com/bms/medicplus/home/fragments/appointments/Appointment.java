package com.bms.medicplus.home.fragments.appointments;

import java.util.Date;

public class Appointment {

    String image;
    String title;
    String type;
    String place;
    Date datetime;

    public Appointment() {
    }

    public Appointment(String image, String title, String type, String place, Date datetime) {
        this.image = image;
        this.title = title;
        this.type = type;
        this.place = place;
        this.datetime = datetime;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public Date getDatetime() {
        return datetime;
    }
}
