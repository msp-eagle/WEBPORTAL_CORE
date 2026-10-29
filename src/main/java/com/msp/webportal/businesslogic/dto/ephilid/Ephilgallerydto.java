package com.msp.webportal.businesslogic.dto.ephilid;

import org.springframework.web.multipart.MultipartFile;

public class Ephilgallerydto {
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
