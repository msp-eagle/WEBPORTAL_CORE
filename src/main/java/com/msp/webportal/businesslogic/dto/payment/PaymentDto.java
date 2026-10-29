package com.msp.webportal.businesslogic.dto.payment;

import lombok.Data;

@Data
public class PaymentDto {
    private String trxnamt;
    private String trxndetails;
    private String trandetail1;
    private String trandetail2;
    private String trandetail3;
    private String trandetail4;
    private String callbackurl;
    private String checksum;
    private String username;
    private String password;
    private String psn;
    private String pcn;

}
