package com.msp.webportal.businesslogic.model.loststolen;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lostStolen")
public class Loststolen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pcn;
    private String uin;
    private String reason;
    private String card;
    private  String model;
    @Column(name = "cr_dtimes")
    private LocalDateTime cr_dTime;}
