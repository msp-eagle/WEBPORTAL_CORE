package com.msp.webportal.businesslogic.dto.resident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.msp.webportal.businesslogic.dto.Otp.ErrorObject;
import com.msp.webportal.businesslogic.dto.Otp.Response;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResidentOtpResponse {
    private String id;
    private String version;
    private String responseTime;
    private Metadata metadata;
    private Response response;
    private String transactionID;
    private ArrayList<ErrorObject> errors;


    @Data
    public static class Metadata {
    }

}
