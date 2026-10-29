package com.msp.webportal.businesslogic.model.home;

import javax.persistence.*;

@Entity
@Table(name="home_image")
public class Homeimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int id;
    @Column(name="home_image")

    private byte[] image;

    public Homeimage(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public Homeimage() {

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
