package com.msp.webportal.businesslogic.model.adminmagement;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "admin_magement_creation")
public class AdminManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String role;


}
