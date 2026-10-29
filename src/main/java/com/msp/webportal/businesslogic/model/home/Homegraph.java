package com.msp.webportal.businesslogic.model.home;

import javax.persistence.*;

@Entity
@Table(name="home_graph")
public class Homegraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int id;
    @Column(name="home_graph")

    private byte[] image;

    public Homegraph(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public Homegraph() {

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
