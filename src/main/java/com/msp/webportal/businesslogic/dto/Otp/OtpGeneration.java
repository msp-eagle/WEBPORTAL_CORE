package com.msp.webportal.businesslogic.dto.Otp;

import lombok.Data;

@Data

public class OtpGeneration{
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
}

