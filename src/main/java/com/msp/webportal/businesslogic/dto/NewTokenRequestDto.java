package com.msp.webportal.businesslogic.dto;

import lombok.Data;

@Data
public class NewTokenRequestDto {
    private String id;

    /**
     * The ver.
     */
    private String version;

    /**
     * The timestamp.
     */
    private String requesttime;


    private ClientIdSecretKeyRequestDto request;
}
