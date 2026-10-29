package com.msp.webportal.businesslogic.model.login;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="captcha")
public class Captcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_Ip")
    private String deviceIp;
    @Column(name = "captcha_string")
    private String captchaString;
    @Column(name = "is_Active")
    private boolean isActive;
    @Column(name = "expired_Time")
    private LocalDateTime expiredTime;


}
