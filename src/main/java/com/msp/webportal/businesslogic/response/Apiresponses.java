package com.msp.webportal.businesslogic.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Apiresponses {

    private boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalRecords;
    private Object data;

    public Apiresponses() {
    }

    public Apiresponses(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Apiresponses(boolean status, Long totalRecords, Object data) {
        this.status = status;
        this.totalRecords = totalRecords;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
