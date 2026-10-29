package com.msp.webportal.businesslogic.dto.resident;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDemoValidateOtp {
    private String identityJson;
    private String individualId;
    private String otp;
    private String transactionID;
}
