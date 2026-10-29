package com.msp.webportal.businesslogic.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private String status;
//    private String smsstatus;  private String smsstatus;
    private String error;
    private Object data;
    private long requestTime;
    private long responseTime;

}
