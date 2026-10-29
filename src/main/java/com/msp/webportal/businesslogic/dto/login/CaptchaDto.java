package com.msp.webportal.businesslogic.dto.login;

public class CaptchaDto {

    public byte[] getCaptch() {
        return captch;
    }

    public void setCaptch(byte[] captch) {
        this.captch = captch;
    }

    private byte[] captch;

}
