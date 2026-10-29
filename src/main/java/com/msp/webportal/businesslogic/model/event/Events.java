package com.msp.webportal.businesslogic.model.event;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="table_event")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int id;
    @Column(name="event_title")
    private String eventitle;
    @Column(name="event_description")
    private String desc;
    @Column(name="event_image")

    private byte[] image;
    @Column(name="event_todate")

    private LocalDate todate;


    @Column(name="event_time")
    private LocalTime time;

    public Events(){

    }

    public Events(int id, String eventitle, String desc, byte[] image, LocalDate todate, LocalTime time) {
        this.id = id;
        this.eventitle = eventitle;
        this.desc = desc;
        this.image = image;
        this.todate = todate;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventitle() {
        return eventitle;
    }

    public void setEventitle(String eventitle) {
        this.eventitle = eventitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDate getTodate() {
        return todate;
    }

    public void setTodate(LocalDate todate) {
        this.todate = todate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}


