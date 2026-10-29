package com.msp.webportal.businesslogic.dto;

import lombok.Data;

@Data
public class ClientIdSecretKeyRequestDto {
    private String userName;
    private String password;
    private String appId;
}
