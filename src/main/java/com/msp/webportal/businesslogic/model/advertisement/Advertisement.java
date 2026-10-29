package com.msp.webportal.businesslogic.model.advertisement;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name="advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int id;
    @Column(name=" ad_title")

    private String advtitle;
    @Column(name="ad_description")
    private String advdesc;
    @Column(name="ad_image")

    private byte[] image;
    @Column(name="ad_todate")

    private LocalDate advdate;

    @Column(name="ad_time")

    private LocalTime advtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvtitle() {
        return advtitle;
    }

    public void setAdvtitle(String advtitle) {
        this.advtitle = advtitle;
    }

    public String getAdvdesc() {
        return advdesc;
    }

    public void setAdvdesc(String advdesc) {
        this.advdesc = advdesc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDate getAdvdate() {
        return advdate;
    }

    public void setAdvdate(LocalDate advdate) {
        this.advdate = advdate;
    }

    public LocalTime getAdvtime() {
        return advtime;
    }

    public void setAdvtime(LocalTime advtime) {
        this.advtime = advtime;
    }

    public Advertisement() {
    }

    public Advertisement(int id, String advtitle, String advdesc, byte[] image, LocalDate advdate, LocalTime advtime) {
        this.id = id;
        this.advtitle = advtitle;
        this.advdesc = advdesc;
        this.image = image;
        this.advdate = advdate;
        this.advtime = advtime;
    }
}

