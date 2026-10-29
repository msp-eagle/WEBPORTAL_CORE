package com.msp.webportal.businesslogic.dto.trackstatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Authstatus {
    private String responsetime;
    private List<Error> errors;
    private ResponseData response;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class ResponseData {
        private List<AuthType> authTypes;
    }
    @Data
    public static class Error {
        private String errorCode;
        private String message;
    }
@Data
    public static class AuthType {
        private String authType;
        private String authSubType;
        private boolean locked;
        private String user;
        private LocalDateTime lockStartTime;
        private LocalDateTime lockEndTime;
    }

}
