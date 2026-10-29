package com.msp.webportal.businesslogic.model.event;

import javax.persistence.*;
@Entity
@Table(name="event_image")
public class EventImage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="sno")
        private int id;
        @Column(name="image")
        private byte[] image;

    public EventImage(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public EventImage() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
