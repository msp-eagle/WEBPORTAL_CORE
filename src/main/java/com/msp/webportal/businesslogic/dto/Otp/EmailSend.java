package com.msp.webportal.businesslogic.dto.Otp;

import lombok.Data;

@Data
public class EmailSend {
    private String attachments;
    private String mailContent;
    private String mailSubject;
    private String mailTo;

}
