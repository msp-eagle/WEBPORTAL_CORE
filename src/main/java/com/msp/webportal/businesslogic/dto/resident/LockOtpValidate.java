package com.msp.webportal.businesslogic.dto.resident;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LockOtpValidate {
    private String individualId;
    private String otp;
    private String transactionID;
    private LocalDateTime expiryTimeStart;
    private LocalDateTime expiryTime;
}
