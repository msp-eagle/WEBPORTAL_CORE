package com.msp.webportal.businesslogic.dto.Otp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorObject {
    private String errorCode;
    private String errorMessage;
    private String message;// errorMessage and message is same in different api it will come in different form

}