package com.msp.webportal.businesslogic.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostPaymentResponseDto<T> {
    private String status;
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private java.lang.Error error;
    private long requestTime;
    private long responseTime;

}