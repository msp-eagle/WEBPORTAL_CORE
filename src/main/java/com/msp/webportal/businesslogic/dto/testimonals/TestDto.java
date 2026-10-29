package com.msp.webportal.businesslogic.dto.testimonals;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class TestDto {

    private Long id;
    private String title;
    private String description;
    private MultipartFile image;
    private String address;
    private LocalDateTime createdAt;


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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}
}
