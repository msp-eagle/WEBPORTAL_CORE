package com.msp.webportal.businesslogic.response;

import lombok.Data;
@Data
public class Smsdata {

        private String name;
        private Integer otp;
        private Long mobile;
        private String msg;
        private String purpose;

}
