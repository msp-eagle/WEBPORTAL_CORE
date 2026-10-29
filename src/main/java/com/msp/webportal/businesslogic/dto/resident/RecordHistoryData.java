package com.msp.webportal.businesslogic.dto.resident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordHistoryData {
    private int serialNumber;
    private String idUsed;
    private String authModality;
    private String date;
    private String time;
    private String partnerName;
    private String partnerTransactionId;
    private String authResponse;
    private String responseCode;
    private String attributes;
}
