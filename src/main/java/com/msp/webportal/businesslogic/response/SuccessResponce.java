package com.msp.webportal.businesslogic.response;

import org.springframework.http.HttpStatus;

public class SuccessResponce {

    private boolean Status;
    private int  status;
    private String message;
    private Object userInfo;
    private Object data;
    public SuccessResponce(boolean status, String message, Object data){
        this.Status=status;
        this.message=message;
        this.data=data;
    }

    public SuccessResponce(String message) {
        this.message = message;
    }

    /**
     * @param  status set status for the request
     * @param  message message For the request
     *
     * */
    public SuccessResponce(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public SuccessResponce(HttpStatus status, String message, Object userInfo) {
        this.status = status.value();
        this.message = message;
        this.userInfo = userInfo;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }
}
