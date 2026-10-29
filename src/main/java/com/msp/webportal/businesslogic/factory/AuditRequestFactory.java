package com.msp.webportal.businesslogic.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.constant.AuditEvents;
import com.msp.webportal.businesslogic.constant.AuditModule;
import com.msp.webportal.businesslogic.constant.PwpConfigKeyConstant;
import com.msp.webportal.businesslogic.dto.audit.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Component
public class AuditRequestFactory {
    private static final String DEFAULT_HOST_NAME = "localhost";

    private static final String DEFAULT_HOST_ADDRESS = "127.0.0.1";

    /** The mosipLogger. */

    /** The env. */
    @Autowired
    private Environment env;

    @Autowired
    private Gson gson;
    /**
     * Builds the request.
     *
     * @param module the module
     * @param event the event
     * @param id the id
     * @param idType the id type enum
     * @param desc the desc
     * @return the audit request dto
     */
//    public RequestWrapper<AuditRequestDto> buildRequest(AuditModule module, AuditEvents event, String id, IdType idType, String desc) throws JsonProcessingException {
//        return buildRequest(module, event, id, idType.name(), desc);
//    }

    /**
     * Builds the request.
     *
     * @param module the module
     * @param event the event
     * @param id the id
     * @param idType the id type name
     * @param desc the desc
     * @return the audit request dto
     */
    public RequestWrapper<AuditRequestDto> buildRequest(AuditModule module, AuditEvents event, String id, String idType, String desc) throws JsonProcessingException {
        AuditRequestDto request = new AuditRequestDto();
        String hostName;
        String hostAddress;

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
            hostAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            hostName = env.getProperty(PwpConfigKeyConstant.AUDIT_DEFAULT_HOST_NAME, DEFAULT_HOST_NAME);
            hostAddress = env.getProperty(PwpConfigKeyConstant.AUDIT_DEFAULT_HOST_ADDRESS, DEFAULT_HOST_ADDRESS);
        }

        request.setEventId(event.getEventId());
        request.setEventName(event.getEventName());
        request.setEventType(event.getEventType());
        request.setActionTimeStamp(ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        request.setHostName(hostName);
        request.setHostIp(hostAddress);
        request.setApplicationId(env.getProperty(PwpConfigKeyConstant.APPLICATION_ID));
        request.setApplicationName(env.getProperty(PwpConfigKeyConstant.APPLICATION_NAME));
        request.setSessionUserId(env.getProperty("auth.appid"));
        request.setSessionUserName(env.getProperty("auth.username"));
        request.setIdType(idType);
        request.setCreatedBy(env.getProperty(PwpConfigKeyConstant.USER_NAME));
        request.setModuleName(module.getModuleName());
        request.setModuleId(module.getModuleId());
        request.setDescription(desc);
        request.setId(id);
        return createRequest(request);
    }
    public <T> RequestWrapper<T> createRequest(T t) throws JsonProcessingException {
        RequestWrapper<T> request = new RequestWrapper<>();
        request.setRequest(t);
        request.setId("pwp");
        request.setRequestTime(ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        request.setVersion("v1");
        System.out.println("request:" +gson.toJson(request));
        String url = env.getProperty("audit.rest.uri");
        String method = env.getProperty("audit.rest.httpMethod");
        String record = ApiConnection.connectApi(url,
                "POST", gson.toJson(request), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> stringObjectMap = objectMapper.readValue(record, Map.class);
        AuditResponceDto response = gson.fromJson(gson.toJson(stringObjectMap.get("response")), AuditResponceDto.class);
        return request;
    }
}
