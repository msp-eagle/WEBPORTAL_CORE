package com.msp.webportal.businesslogic.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
class Error{
      private String error_message;
              private String status;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PcnDobResponseDto {
      private String DOB;
      private String PCN;
    private Error error;
    public String getErrorMessage() {
        if(error == null){
            return null;
        }
        return error.getError_message();
    }






}
