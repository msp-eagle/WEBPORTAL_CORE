package com.msp.webportal.businesslogic.dto.resident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.msp.webportal.businesslogic.dto.Otp.ErrorObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordAuthResponse {

    private String id;
    private String version;
    private String responseTime;
    private Metadata metadata;
    private List<RecordAuthstatusData> response;
    private String transactionID;
    private ArrayList<ErrorObject> errors;

    @Data
    private static class Metadata {
    }
    
}
