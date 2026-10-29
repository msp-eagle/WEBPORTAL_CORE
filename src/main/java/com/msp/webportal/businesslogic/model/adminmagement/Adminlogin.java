package com.msp.webportal.businesslogic.model.adminmagement;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_magement")
public class Adminlogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

}
