package com.msp.webportal.businesslogic.response;

import java.util.Map;
import java.util.Set;

public class Apiresponce {

    private String responseType;



    private String status;
    private Object responseData;


    private Map<String, Set<String>> validationErrors;

    public Apiresponce() {
    }

    public Apiresponce(String responseType, String status, Object responseData, Map<String, Set<String>> validationErrors) {
        this.responseType = responseType;
        this.responseData = responseData;
        this.validationErrors = validationErrors;
        this.status = status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public Map<String, Set<String>> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, Set<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
