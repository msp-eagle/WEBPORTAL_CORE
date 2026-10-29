package com.msp.webportal.businesslogic.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Smserrorresponse {
    @JsonProperty("MessageErrorCode")
    private Integer  MessageErrorCode;
    @JsonProperty("MessageErrorDescription")
    private String MessageErrorDescription;
    @JsonProperty("MobileNumber")
    private String MobileNumber;
    @JsonProperty("MessageId")
    private String MessageId;
    @JsonProperty("Custom")
    private String Custom;
}
