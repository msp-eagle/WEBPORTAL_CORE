package com.msp.webportal.businesslogic.service.trackStatus;

import java.util.Arrays;
import java.util.*;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msp.webportal.businesslogic.commonUtil.UniqueIdGenerator;
import com.msp.webportal.businesslogic.dto.resident.*;
import com.msp.webportal.businesslogic.dto.trackstatus.ViewAlyasPSN;
import com.msp.webportal.businesslogic.idrepoRepository.VidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.response.CustomResponse;

@Service
public class ResidentOtpService {

    @Autowired
    Environment environment;
    @Autowired
    private VidRepository vidRepository;

    public CustomResponse residentOtpRequest(String id, String type)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("resident.otp.request");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/req/otp";
        ResidentOtpRequest otpRequest = ResidentOtpRequest.builder().id("mosip.identity.otp.internal")
                .individualId(id).individualIdType(type).otpChannel(Arrays.asList("email","phone"))
                .version("1.0").requestTime(ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()))
                .transactionID(UniqueIdGenerator.generateRandomTransactionId())
                .build();
        String otpRequestString = otpRequest.toString();
        System.out.println("In string " + otpRequest.toString());
        String record = ApiConnection.connectApi(requestOtp,
                "POST", otpRequestString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
        CustomResponse customResponse = new CustomResponse();
        if (otpResponse.getResponse() != null) {
            customResponse.setData(otpResponse);
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
    public CustomResponse OtpValidate(OtpValidatedto otpValidatedto)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("resident.otp.validate");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid";
        // Creating the authType array list

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.vid");
        System.out.println(otpValidatedto.toString());
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("individualId", otpValidatedto.getIndividualId());
        request.put("individualIdType", "VID");
        request.put("otp", otpValidatedto.getOtp());
        request.put("transactionID", otpValidatedto.getTransactionID());
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
        CustomResponse customResponse = new CustomResponse();
        if (otpResponse.getErrors() == null || otpResponse.getErrors().isEmpty()) {
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
        }
        return customResponse;
    }

    public CustomResponse residentOtpValidate(ResidentOtpValidate residentOtpValidate)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("alyas.psn.create.url");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid";

    
        System.out.println("TO STRING" + residentOtpValidate.toString());
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.vid");
        System.out.println(residentOtpValidate.toString());
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("expiryTime", residentOtpValidate.getExpiryTime());// check
        request.put("individualId", residentOtpValidate.getIndividualId());
        request.put("individualIdType", "VID");
//        request.put("otp", residentOtpValidate.getOtp());
//        request.put("transactionID", residentOtpValidate.getTransactionID());
        request.put("userSharableAttributes", residentOtpValidate.getUserSharableAttributes());
        request.put("vidType", "TEMPORARY");
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
        CustomResponse customResponse = new CustomResponse();
        if (otpResponse.getResponse() != null) {
            // if(otpResponse.getResponse().getStatus().equals("failure")){
            // customResponse.setStatus("failed");
            // customResponse.setError("OTP is invalid");
            // }
            // else{
            System.out.println("===============" + otpResponse.getResponse());
            customResponse.setData(otpResponse.getResponse());
            customResponse.setStatus("success");
            // }
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
        }
        System.out.println(customResponse.toString());
        return customResponse;
    }

    public CustomResponse lockOtpValidate(LockOtpValidate residentOtpValidate)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("auth.lock.url");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid";
        // Creating the authType array list
        List<String> authTypeList = List.of("demo", "bio-Iris", "bio-FACE", "bio-Finger");

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.authlock");
        System.out.println(residentOtpValidate.toString());
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("authType", authTypeList);
        request.put("individualId", residentOtpValidate.getIndividualId());
        request.put("individualIdType", "VID");
        request.put("otp", residentOtpValidate.getOtp());
        request.put("expiryTimeStart",residentOtpValidate.getExpiryTimeStart());
        request.put("expiryTimeEnd",residentOtpValidate.getExpiryTime());
        request.put("transactionID", residentOtpValidate.getTransactionID());
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonString = objectMapper.writeValueAsString(jsonData);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
//        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
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
        }
        return customResponse;
    }

    public CustomResponse unlockOtpValidate(LockOtpValidate residentOtpValidate)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("auth.unlock.url");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid";
        // Creating the authType array list
        List<String> authTypeList = List.of("demo", "bio-Iris", "bio-FACE", "bio-Finger");

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.authunlock");
        System.out.println(residentOtpValidate.toString());
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("authType", authTypeList);
        request.put("individualId", residentOtpValidate.getIndividualId());
        request.put("individualIdType", "VID");
        request.put("otp", residentOtpValidate.getOtp());
        request.put("transactionID", residentOtpValidate.getTransactionID());
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
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
        }
        return customResponse;
    }

    public ViewAlyasPSN viewalyasRequest(String id) throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("view.alyas.psn.url");
        String template = environment.getProperty("template");
        String url = requestOtp + "/" + id;

        String record = ApiConnection.connectApi(url,
                "POST", template.toString(), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        ViewAlyasPSN otpResponse = objectMapper.readValue(record, ViewAlyasPSN.class);
        return otpResponse;
    }

    public CustomResponse otpValidateForDeactivateAndDeleteAlyas(ResidentOtpValidate residentOtpValidate, String vid)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("delete.psn.url");
        String url = requestOtp + "/" + vid;
//        String requestOtp = "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid/status/" + vid;
        System.out.println("Url: " + url);
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.vidstatus");
        System.out.println(residentOtpValidate.toString());
        Map<String, Object> request = new HashMap<>();
        request.put("individualId", residentOtpValidate.getIndividualId());
        request.put("individualIdType", "VID");
        request.put("otp", residentOtpValidate.getOtp());
        request.put("transactionID", residentOtpValidate.getTransactionID());
        request.put("vidStatus", residentOtpValidate.getVidStatus());
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println(jsonString);
        CustomResponse customResponse = new CustomResponse();
        try {
            String record = ApiConnection.connectApiThroughWebClient(url,
                    jsonString, "PATCH");
            System.out.println("Record" + record);
            ObjectMapper objectMapper = new ObjectMapper();
            ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
            if (otpResponse.getResponse() != null) {
                customResponse.setData(otpResponse.getResponse());
                customResponse.setStatus("success");
            } else if (otpResponse.getErrors() != null) {
                String error = "";
                if(Objects.equals(otpResponse.getErrors().get(0).getErrorCode(), "RES-SER-418")) {
                    if(changeAlyasStatusToDeleted(vid))
                        customResponse.setStatus("success");
                    else
                        customResponse.setStatus("failed");
                }
                else{
                    customResponse.setStatus("failed");
                    if (otpResponse.getErrors().get(0).getErrorMessage() != null) {
                        error = otpResponse.getErrors().get(0).getErrorMessage();
                    } else if (otpResponse.getErrors().get(0).getMessage() != null) {
                        error = otpResponse.getErrors().get(0).getMessage();
                    } else {
                        error = "Something wrong happened, try again later";
                    }
                }

                customResponse.setError(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customResponse;
    }


    private Boolean changeAlyasStatusToDeleted(String individualId){
        try{
            int val = vidRepository.changeAlyasStatusToDeleted(individualId);
            return val > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
    public CustomResponse lostStolenRequest(ResidentOtpValidate residentOtpValidate)
            throws JsonMappingException, JsonProcessingException {
        String requestOtp = environment.getProperty("alyas.psn.create.url");
        // String requestOtp =
        // "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/vid";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.vid");
        System.out.println(residentOtpValidate.toString());
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("individualId", residentOtpValidate.getIndividualId());
        request.put("individualIdType", "VID");
//        request.put("otp", residentOtpValidate.getOtp());
        request.put("issuePrint", residentOtpValidate.getIssueStatus());
        request.put("vidType", "PERPETUAL");
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(requestOtp,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
        ResidentOtpResponse otpResponse = objectMapper.readValue(record, ResidentOtpResponse.class);
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
        }
        return customResponse;
    }

}
