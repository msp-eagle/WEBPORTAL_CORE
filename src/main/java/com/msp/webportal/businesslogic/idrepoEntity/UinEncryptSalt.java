package com.msp.webportal.businesslogic.idrepoEntity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name = "uin_encrypt_salt")
@Data
public class UinEncryptSalt {


    @Id
    private int id;
    @Column(name = "salt")
    private String salt;
    @Column(name = "cr_by")
    private String createdBy;
    @Column(name = "cr_dtimes")
    private LocalDateTime createdDTimes;
    @Column(name = "upd_by")
    private String updatedBy;
    @Column(name = "upd_dtimes")
    private LocalDateTime updatedDTimes;

}