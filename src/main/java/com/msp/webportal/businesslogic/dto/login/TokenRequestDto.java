package com.msp.webportal.businesslogic.dto.login;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TokenRequestDto implements Serializable {
    private static final Long serialVersionUID = 5L;

    private String username;
    private String password;
    private String captcha;
    private LocalDateTime expiredTime;

    public TokenRequestDto(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}