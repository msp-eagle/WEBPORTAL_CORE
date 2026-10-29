package com.msp.webportal.businesslogic.service.trackStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.trackstatus.Authstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AuthStatusService {


    @Autowired
    Environment environment;
    public Authstatus authstatusrequest(String status) throws JsonMappingException, JsonProcessingException {
        String trnsts = environment.getProperty("auth.status.request");
        String template = environment.getProperty("template");
//        String url = trnsts + "/status/" + status;
        String record = ApiConnection.connectApi(trnsts,
                "POST", status, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
//        System.out.println("Records"+record);
        Authstatus authresponse = objectMapper.readValue(record, Authstatus.class);
        System.out.println("authstatsu"+authresponse);
        return authresponse;
    }
}
