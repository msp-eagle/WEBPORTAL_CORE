package com.msp.webportal.businesslogic.dto.Otp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtpGenerationResponse {
    private String id;
    private Response response; // Ensure this is correctly typed
    private LocalDateTime requestTime;
    private String version;
    private String responsetime;
    private List<ErrorObject> errors; // Adjust this line to match the actual structure

    @Data
    public static class ErrorObject {
        private String errorCode;
        private String message;
        private String errorMessage;
    }

}
