package com.msp.webportal.businesslogic.dto.home;

import org.springframework.web.multipart.MultipartFile;

public class homegraphdto {
    private int id;
    private MultipartFile image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
