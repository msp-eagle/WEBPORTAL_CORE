package com.msp.webportal.businesslogic.dto.advertisement;

import org.springframework.web.multipart.MultipartFile;

public class Advertisementdto {
    private int id;
    private MultipartFile image;
    private String advtitle;
    private String advdesc;

    private String advdate;

    private String advtime;

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

    public String getAdvdate() {
        return advdate;
    }

    public void setAdvdate(String advdate) {
        this.advdate = advdate;
    }

    public String getAdvtime() {
        return advtime;
    }

    public void setAdvtime(String advtime) {
        this.advtime = advtime;
    }
}
