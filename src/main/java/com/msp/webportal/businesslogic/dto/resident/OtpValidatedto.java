package com.msp.webportal.businesslogic.dto.resident;

import lombok.Data;

@Data
public class OtpValidatedto {
    private String individualId;
    private String individualIdType;
    private String otp;
    private String transactionID;
}
