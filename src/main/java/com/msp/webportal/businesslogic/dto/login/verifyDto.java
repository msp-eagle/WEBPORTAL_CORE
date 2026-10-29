package com.msp.webportal.businesslogic.dto.login;

public class verifyDto {
    private String captcha;


    public String getCaptcha()
    {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

//    public LocalDateTime getExpiredTime() {
//        return expiredTime;
//    }
//
//    public void setExpiredTime(LocalDateTime expiredTime) {
//        this.expiredTime = expiredTime;
//    }
}
