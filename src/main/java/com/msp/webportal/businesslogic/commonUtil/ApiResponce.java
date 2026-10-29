package com.msp.webportal.businesslogic.commonUtil;
//
//import com.eaglesoftware.karnalsmartcity.dto.calender.CalenderViewDto;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * ApiResponce Model was the Return Type  for All successful  Request
 * @author Ashokmiltan
 * @since 1.0

 *
 */
public class ApiResponce {
    private boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalRecords;
    private Object data;


    public ApiResponce() {
    }

    public ApiResponce(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

//    public ApiResponce(boolean status, Long totalRecords, Object data) {
//        this.status = status;
//        this.totalRecords = totalRecords;
//        this.data = data;
//    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setData(Object data) {
        this.data = data;
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
}
