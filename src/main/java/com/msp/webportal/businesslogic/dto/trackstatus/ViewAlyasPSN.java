package com.msp.webportal.businesslogic.dto.trackstatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewAlyasPSN {
    private String id;
    private String version;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String responsetime;
    private Object metadata;
    private List<VidInfo> response;
    private List<ErrorInfo> errors;





@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorInfo {
    private String errorCode;
    private String message;
}
}

