package com.msp.webportal.businesslogic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class SMSResponse {

        @JsonProperty("ErrorCode")
        private Integer ErrorCode;
        @JsonProperty("ErrorDescription")
        private String ErrorDescription;
        @JsonProperty("Data")

        private ArrayList<Smserrorresponse> Data;

}
