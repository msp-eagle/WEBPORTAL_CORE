package com.msp.webportal.businesslogic.dto.Otp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

        @JsonProperty("otp") 
        private String otp;
        @JsonProperty("status")
        private String status;
        @JsonProperty("vid") 
        private String vid;
        @JsonProperty("message")
        private String message;
        


}
