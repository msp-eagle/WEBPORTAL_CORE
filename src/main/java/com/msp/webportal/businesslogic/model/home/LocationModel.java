package com.msp.webportal.businesslogic.model.home;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "registration_center")
public class LocationModel {


    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "addr_line2")
    private String addr_line2;
    @Column(name = "addr_line3")
    private String addr_line3;
    @Transient  // distance is not persisted in the database
    private double distance;

}
