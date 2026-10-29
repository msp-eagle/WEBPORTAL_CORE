package com.msp.webportal.businesslogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private boolean status;
    private String message;
    private String info;

    public ResourceNotFound(boolean status, String message, String info){
        super();
        this.status = status;
        this.message = message;
        this.info = info;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
