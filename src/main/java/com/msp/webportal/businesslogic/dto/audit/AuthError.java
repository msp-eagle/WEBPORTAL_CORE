package com.msp.webportal.businesslogic.dto.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthError {

    private String errorCode;

    private String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public AuthError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
