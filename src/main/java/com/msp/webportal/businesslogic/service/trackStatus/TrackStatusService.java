package com.msp.webportal.businesslogic.service.trackStatus;

import com.msp.webportal.businesslogic.dto.trackstatus.Trnresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.Otp.OtpGenerationResponse;
import com.msp.webportal.businesslogic.dto.Otp.Response;

@Service
public class TrackStatusService {

    @Autowired
    Environment environment;

    public Response RetrievePcnOtprequest(String trn) throws JsonMappingException, JsonProcessingException {
        String retriveOtp = environment.getProperty("retrieve.otp.request");
        String template = environment.getProperty("template");
        String url = retriveOtp + "/request?trn=" + trn;
        String record = ApiConnection.connectApi(url,
                "POST", template.toString(), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        return otpResponse.getResponse();
    }

    public Boolean RetrievePcnOtpValidate(String trn, String otp) throws JsonMappingException, JsonProcessingException {
        String retriveOtp = environment.getProperty("retrieve.otp.request");
        String template = environment.getProperty("template");
        String url = retriveOtp + "/validate?otp="+otp+ "&trn=" + trn;
        ObjectMapper objectMapper = new ObjectMapper();
        String record = ApiConnection.connectApi(url,
                "POST", template.toString(), "Cookie");
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        if(otpResponse.getErrors() == null){
            return true;
        }
        else{
            return false;
        }
    }
    public Trnresponse trnstatusrequest(String trn) throws JsonMappingException, JsonProcessingException {
        String trnsts = environment.getProperty("track.status.request");
        String template = environment.getProperty("template");
        String url = trnsts + "/status/" + trn;
        String record = ApiConnection.connectApi(url,
                "POST", template.toString(), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        Trnresponse trnresponse = objectMapper.readValue(record, Trnresponse.class);
        return trnresponse;
    }
}
