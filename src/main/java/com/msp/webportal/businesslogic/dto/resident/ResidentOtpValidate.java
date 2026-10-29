package com.msp.webportal.businesslogic.dto.resident;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResidentOtpValidate {
    private String individualId;
    private String individualIdType;
    private String expiryTime;
    private String otp;
    private String transactionID;
    private String vidStatus;
    private List<String> userSharableAttributes;
    private Boolean issueStatus;
}
