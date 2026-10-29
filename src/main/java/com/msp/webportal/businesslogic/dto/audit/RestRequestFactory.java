package com.msp.webportal.businesslogic.dto.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RestRequestFactory {

    @Autowired
    Environment env;
    public <T> RequestWrapper<T> createRequest(T t) throws JsonProcessingException {
        RequestWrapper<T> request = new RequestWrapper<>();
        request.setRequest(t);
        request.setId("pwp");
        request.setRequestTime(ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));

        String url = env.getProperty("audit.rest.uri");
        String method = env.getProperty("audit.rest.httpMethod");
        String record = ApiConnection.connectApi(url,
                method, request.toString(), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        AuditResponceDto response = objectMapper.readValue(record, AuditResponceDto.class);
        System.out.println("Audlit log fgf fgf te gr grtf gr" +response);
        return request;
    }
}
