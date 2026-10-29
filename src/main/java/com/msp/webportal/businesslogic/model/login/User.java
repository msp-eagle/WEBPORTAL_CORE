package com.msp.webportal.businesslogic.model.login;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usermanagement")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pcn")
    private String pcn;



    @Column(name = "password")
    private String password;

    @Column(name = "c_password")
    private String confirmPassword;


    private String status;
    private Integer count;

   private String dob;

   private LocalDateTime loggeddate;

    @Column(name = "failure_count")
    private Integer failureCount;

    @Column(name = "failure_count_expired_at")
    private LocalDateTime failureCountExpiredAt;
}
