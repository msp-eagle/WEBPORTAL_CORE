package com.msp.webportal.businesslogic.model.ephilid;

import javax.persistence.*;

@Entity
@Table(name="ephil_image")
public class EphilGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sno")
    private int id;
    @Column(name="ephil_image")
    private byte[] image;

    public EphilGallery(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public EphilGallery() {

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
