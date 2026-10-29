package com.msp.webportal.businesslogic.model.updatedemodata;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="update_userdata")
public class Updatedemodata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String pcn;


    private String status;

    private String responce;


    private LocalDateTime cr_dTime;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPcn() {
        return pcn;
    }

    public void setPcn(String pcn) {
        this.pcn = pcn;
    }

    public String getResponce() {
        return responce;
    }

    public void setResponce(String responce) {
        this.responce = responce;
    }

    public LocalDateTime getCr_dTime() {
        return cr_dTime;
    }

    public void setCr_dTime(LocalDateTime cr_dTime) {
        this.cr_dTime = cr_dTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
