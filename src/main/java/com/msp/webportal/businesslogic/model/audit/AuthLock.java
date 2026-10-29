package com.msp.webportal.businesslogic.model.audit;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "authlockAudit")
public class AuthLock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pcn;

 private String type;
 private String authlockType;
 private LocalDateTime localDateTime;







}
