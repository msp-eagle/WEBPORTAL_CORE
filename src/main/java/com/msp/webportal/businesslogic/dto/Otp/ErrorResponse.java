package com.msp.webportal.businesslogic.dto.Otp;

import lombok.Data;

import java.util.List;
@Data
public class ErrorResponse {
    private String id;
    private String version;
    private String responsetime;
    private Object metadata;
    private List<ErrorInfo> errors;


@Data
public class ErrorInfo {
    private String errorCode;
    private String message;
}
}
