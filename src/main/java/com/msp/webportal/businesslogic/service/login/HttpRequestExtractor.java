package com.msp.webportal.businesslogic.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class HttpRequestExtractor {

    @Autowired(required = true)
    private HttpServletRequest  request;

    private  String clientIp;

    public HttpRequestExtractor(){

    }

    public String getClientIp(){
        this.clientIp =    request.getHeader("X-FORWARDED-FOR");
        if(this.clientIp == null || this.clientIp.isEmpty()){
            this.clientIp = request.getRemoteAddr();
            System.out.println("getRemoteAddr"+ this.clientIp);
        }
        return clientIp;
    }
}

