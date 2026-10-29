package com.msp.webportal.businesslogic.dto.sms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmsRequestDto {
    private String name;
    private Integer otp;
    private Long number;
    private String message;
    private String purpose;

}
