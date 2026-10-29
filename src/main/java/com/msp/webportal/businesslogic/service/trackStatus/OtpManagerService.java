package com.msp.webportal.businesslogic.service.trackStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.Otp.OtpGenerationResponse;
import com.msp.webportal.businesslogic.dto.login.Sms;
import com.msp.webportal.businesslogic.dto.resident.ResidentOtpResponse;
import com.msp.webportal.businesslogic.dto.resident.ResidentOtpValidate;
import com.msp.webportal.businesslogic.dto.trackstatus.Demographic;
import com.msp.webportal.businesslogic.model.otp_log.OTPLog;
import com.msp.webportal.businesslogic.repository.otp_log.OtpLogRepository;
import com.msp.webportal.businesslogic.response.CustomResponse;
import com.msp.webportal.businesslogic.response.SMSResponse;
import com.msp.webportal.businesslogic.service.login.LoginService;
import com.msp.webportal.businesslogic.service.notifier.EmailNotifier;
import com.msp.webportal.businesslogic.service.sms.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OtpManagerService {

    @Autowired
    Environment environment;
    @Autowired
    EmailNotifier emailNotifier;
    @Autowired
    LoginService loginService;
    @Autowired
    OtpLogRepository otpLogRepository;
    @Autowired
    SmsNotificationService smsNotificationService;

    public CustomResponse otpGenerate(String id, String pcn, String purpose)
            throws JsonMappingException, JsonProcessingException {
        CustomResponse customResponse = new CustomResponse();
        if (checkInnumerousOTP(pcn)) {
            customResponse.setStatus("failed");
            customResponse.setError("No. of OTP attempts exceeded");
        } else {
            String url = environment.getProperty("otp.manager.request");
            Map<String, Object> jsonData = new HashMap<>();
            Map<String, Object> request = new HashMap<>();

            Demographic demographic = loginService.demorequest(pcn);
            if (demographic != null) {
                System.out.println("Email id = " + demographic.getEmail());
                request.put("key", demographic.getEmail());
                jsonData.put("request", request);
                jsonData.put("id", id);
                jsonData.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
                jsonData.put("version", "v1");
                Gson gson = new Gson();
                String jsonString = gson.toJson(jsonData);
                System.out.println("json string" + jsonString);
                String record = ApiConnection.connectApi(url, "POST", jsonString, "Cookie");
                ObjectMapper objectMapper = new ObjectMapper();
                OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
                if (otpResponse.getResponse() != null) {
                    System.out.println("GENERATION_SUCCESSFUL");
                    String content = environment.getProperty("sms.otp");
                    String name = demographic.getFirstname() +" "+ demographic.getMiddlename() +" "+ demographic.getLastname();
                    String emailSubject = "Registered Successfully ";


                    if(content != null){
                        content = content.replace("{last name}",name);
                        content = content.replace("{otp}",otpResponse.getResponse().getOtp());
                    }

                    String emailContent = content+"\n\nThank you";
                    if (emailNotifier.sendEmail(otpResponse.getResponse().getOtp(),name,
                            demographic.getEmail(), emailContent)) {
                        customResponse.setStatus("success");
                        Map<String, Object> data = new HashMap<>();
                        data.put("email", demographic.getEmail());
                        customResponse.setData(data);
                        if(!demographic.getMobileno().isEmpty() || !demographic.getMobileno().isBlank()){
                            System.out.println("MOBILE NUMBER: "+ demographic.getMobileno());
                            Long mobile = Long.valueOf(demographic.getMobileno());
                            // SMS Notification
                            smsNotificationService.sendSms(content, mobile);
                        }
                    } else {
                        customResponse.setStatus("failed");
                        customResponse.setError("Otp generation failed. Please try again later.");
                    }
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
            } else { // if unable to access the email id from the pcn
                customResponse.setStatus("failed");
                customResponse.setError("Otp generation failed. Please try again later.");
            }

        }
        return customResponse;
    }

    public Boolean checkInnumerousOTP(String pcn) {
        Optional<OTPLog> otpLog = otpLogRepository.findByPcn(pcn);
        if (otpLog.isPresent()) {
            System.out.println("Current" + LocalDateTime.now() + "Expired" + otpLog.get().getExpiredAt());
            System.out.println("CHECK EXPIRED" + LocalDateTime.now().isAfter(otpLog.get().getExpiredAt()));
            System.out.println("Otp COUNT"+ otpLog.get().getCount());
            if (otpLog.get().getCount() >= 5) {
                if (LocalDateTime.now().isAfter(otpLog.get().getExpiredAt())) {
                    otpLog.get().setCount(1);
                    otpLog.get().setExpiredAt(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
                    otpLogRepository.save(otpLog.get());
                    return false;
                } else {
                    return true;
                }
            } else {
                otpLog.get().setCount(otpLog.get().getCount() + 1);
                otpLogRepository.save(otpLog.get());
                return false;
            }
        } else {
            OTPLog newOtpLog = OTPLog.builder().count(1).createdAt(LocalDateTime.now())
                    .expiredAt(LocalDateTime.now().plus(5, ChronoUnit.MINUTES)).pcn(pcn).build();
            otpLogRepository.save(newOtpLog);
            System.out.println("Current" + LocalDateTime.now() + " Expired Save" + LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
            return false;
        }
    }

    public CustomResponse otpValidate(String otp, String key) throws JsonMappingException, JsonProcessingException {
        System.out.println("Key =" + key);
        String validateotp = environment.getProperty("otp.manager.validate");

        String url = validateotp + key + "&otp="
                + otp;
        String record = ApiConnection.connectApi(url, "GET", "", "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        CustomResponse customResponse = new CustomResponse();
        System.out.println(otpResponse);
        if (otpResponse.getResponse() != null) {
            System.out.println(otpResponse.getResponse());
            if (otpResponse.getResponse().getStatus().equals("failure")) {
                System.out.println("Godson failure");
                customResponse.setStatus("failed");
                customResponse.setError("OTP is invalid");
            } else {
                System.out.println("Godson success");
                customResponse.setStatus("success");
            }
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
//    public SMSResponse smsNotifier(Sms sms)
//            throws JsonMappingException, JsonProcessingException {
//
//    }
}
