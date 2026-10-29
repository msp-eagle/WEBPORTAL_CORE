package com.msp.webportal.businesslogic.service.trackStatus;

import com.msp.webportal.businesslogic.dto.resident.RecordAuthResponse;
import com.msp.webportal.businesslogic.dto.resident.RecordHistoryRequest;
import com.msp.webportal.businesslogic.dto.resident.RecordHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.response.CustomResponse;

@Service
public class RecordHistoryService {

    @Autowired
    Environment environment;

    public CustomResponse getRecordHistory(RecordHistoryRequest recordHistory)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("bio.history.request");

//        String requestOtp = "https://api.apps-external.uat2.phylsys.gov.ph/phylsys-record-history/v1/getHistory";
        Map<String, Object> jsonData = new HashMap<>();
        Map<String, Object> request = new HashMap<>();
        //incase if you want to fetch specific type means this can be used
        request.put("authTypeCode", recordHistory.getAuthTypeCode());
        request.put("individualId", recordHistory.getIndividualId());
        request.put("individualIdType", recordHistory.getIndividualIdType());

        request.put("fromDate", recordHistory.getFromDate());
        request.put("toDate", recordHistory.getToDate());
        jsonData.put("request", request);
        jsonData.put("id", "record.history.retrieve");
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(record);

        RecordHistoryResponse otpResponse = objectMapper.readValue(record, RecordHistoryResponse.class);
        CustomResponse customResponse = new CustomResponse();
        if (otpResponse.getResponse() != null) {
            customResponse.setData(otpResponse.getResponse());
            customResponse.setStatus("success");
        } else if (otpResponse.getErrors() != null) {
            customResponse.setStatus("failed");
            String error;
            if (otpResponse.getErrors().get(0).getErrorMessage() != null) {
                error = otpResponse.getErrors().get(0).getErrorMessage();
            } else if (otpResponse.getErrors().get(0).getMessage() != null) {
                error = otpResponse.getErrors().get(0).getMessage();
            } else {
                error = "Something wrong happened, try again later";
            }
            customResponse.setError(error);
        } else {
            customResponse.setStatus("failed");
            customResponse.setError("Something wrong happened, try again later");
        }
        return customResponse;
    }

    public CustomResponse getAuthstatusRecordHistory(RecordHistoryRequest recordHistory)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("auth.history.request");

//        String requestOtp = "https://api.apps-external.uat2.phylsys.gov.ph/phylsys-record-history/v1/getAuthstatus/transactionHistory";
        Map<String, Object> jsonData = new HashMap<>();
        Map<String, Object> request = new HashMap<>();
        //incase if you want to fetch specific type means this can be used
        //request.put("authTypeCode", recordHistory.getAuthTypeCode());
        request.put("individualId", recordHistory.getIndividualId());
        request.put("individualIdType", recordHistory.getIndividualIdType());
        request.put("fromDate", recordHistory.getFromDate());
        request.put("toDate", recordHistory.getToDate());
        jsonData.put("request", request);
        jsonData.put("id", "record.history.retrieve");
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("Jfdfg"+jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(record);

        RecordAuthResponse otpResponse = objectMapper.readValue(record, RecordAuthResponse.class);
        CustomResponse customResponse = new CustomResponse();
        if (otpResponse.getResponse() != null) {
            customResponse.setData(otpResponse.getResponse());
            customResponse.setStatus("success");
        } else if (otpResponse.getErrors() != null) {
            customResponse.setStatus("failed");
            String error;
            if (otpResponse.getErrors().get(0).getErrorMessage() != null) {
                error = otpResponse.getErrors().get(0).getErrorMessage();
            } else if (otpResponse.getErrors().get(0).getMessage() != null) {
                error = otpResponse.getErrors().get(0).getMessage();
            } else {
                error = "Something wrong happened, try again later";
            }
            customResponse.setError(error);
        } else {
            customResponse.setStatus("failed");
            customResponse.setError("Something wrong happened, try again later");
        }
        return customResponse;
    }

}
