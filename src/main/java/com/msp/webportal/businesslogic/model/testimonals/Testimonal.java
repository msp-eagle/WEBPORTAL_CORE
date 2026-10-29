package com.msp.webportal.businesslogic.model.testimonals;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "testimonals")
public class Testimonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private byte[] image;
    private String address;

    @CreatedDate
    private LocalDateTime createdAt;

    public Testimonal(){

    }

    public Testimonal(Long id, String title, String description, byte[] image, String address, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.address = address;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getImage() {return image;}

    public void setImage(byte[] image) {this.image = image;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}
}
