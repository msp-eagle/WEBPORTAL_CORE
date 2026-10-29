package com.msp.webportal.businesslogic.service.updatedemodata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.resident.ResidentOtpResponse;
import com.msp.webportal.businesslogic.dto.resident.UpdateDemoValidateOtp;
import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.about.phisyscouncil.Compotition;
import com.msp.webportal.businesslogic.model.updatedemodata.Updatedemodata;
import com.msp.webportal.businesslogic.repository.updatedemodata.UpdatedemodataRepository;
import com.msp.webportal.businesslogic.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdatedemodataService {

    @Autowired
    UpdatedemodataRepository updatedemodataRepository;

    @Autowired
    Environment environment;

    public Updatedemodata save(Updatedemodata about){
        Updatedemodata updatedemodata=new Updatedemodata();
        LocalDateTime cr_dtime = LocalDateTime.now();
        updatedemodata.setPcn(about.getPcn());
        updatedemodata.setCr_dTime(cr_dtime);
        updatedemodata.setResponce(about.getResponce());
        updatedemodata.setStatus("UPDATE");
        Updatedemodata save = updatedemodataRepository.save(updatedemodata);
        return save;
    }
    public CustomResponse otpValidate(UpdateDemoValidateOtp updateDemoValidateOtp)
            throws JsonMappingException, JsonProcessingException {
        String url = environment.getProperty("resident.update.url");
//        String url = "https://api.apps-external.uat2.phylsys.gov.ph/resident/v1/req/update-uin";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", "mosip.resident.updateuin");
        // Creating the request object
        Map<String, Object> request = new HashMap<>();
        request.put("identityJson", updateDemoValidateOtp.getIdentityJson());// check
        request.put("individualId", updateDemoValidateOtp.getIndividualId());
        request.put("individualIdType", "VID");
        request.put("otp", updateDemoValidateOtp.getOtp());
        request.put("transactionID", updateDemoValidateOtp.getTransactionID());
        jsonData.put("request", request);
        jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
        jsonData.put("version", "v1");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(url,
                "POST", jsonString, "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("record: " + record);
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

}
