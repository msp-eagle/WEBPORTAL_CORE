package com.msp.webportal.businesslogic.service.notifier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.msp.webportal.businesslogic.response.SMSResponse;
import com.msp.webportal.businesslogic.response.Smsdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.Otp.EmailSend;
import com.msp.webportal.businesslogic.dto.Otp.OtpGenerationResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailNotifier {
    @Autowired
    Environment environment;

    public Boolean sendEmail(String otp, String name, String email, String emailContent) throws JsonProcessingException {
        EmailSend emailSend = new EmailSend();
        emailSend.setMailContent(emailContent);
        emailSend.setMailTo(email);
        emailSend.setMailSubject("PhilSys Web Portal One-Time-Password(OTP)[SYSTEM GENERATED-DO NOT REPLY]");
        String record = ApiConnection.connectApiForMultipart(
                "https://api.apps-external.uat2.phylsys.gov.ph/v1/notifier/email/send",
                emailSend, "POST");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(record);
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        return otpResponse.getResponse().getStatus().equals("success");
    }

    public SMSResponse sendSms(String otp, String name, String mobile, String purpose) throws JsonProcessingException {
        String Smsapi = environment.getProperty("sms.notifier.url");
        Boolean result;
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("senderId", "PSA");// check
        jsonData.put("is_Unicode", true);
        jsonData.put("is_Flash", false);
        jsonData.put("schedTime", "");
        jsonData.put("groupId", "");
        jsonData.put("message",
                "PhilSys Web Portal One-Time-Password(OTP)[SYSTEM GENERATED-DO NOT REPLY]"+"\n"+
                        "Dear " + name + ",\n"
                        + "Thank you for using the PhilSys Web Portal. To proceed, please use this OTP for " + purpose
                        + ":\n" + otp
                        + "\nThis is only valid for 3 minutes. Do not share your OTP with anyone.\nThis message is intended for the owner of this email address and contains confidential information. If you did not request for this OTP, please contact us at PhilSys Hotline 1388 (fees may apply) or e-mail us at info@philsys.gov.ph.");

        jsonData.put("mobileNumbers", "639913496882");
        jsonData.put("serviceId", "");
        jsonData.put("coRelator", "");
        jsonData.put("linkId", "");
        jsonData.put("principleEntityId", "");
        jsonData.put("templateId", "");
        jsonData.put("apiKey", environment.getProperty("otp.api.key"));
        jsonData.put("clientId", environment.getProperty("otp.client.id"));
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonData);
        System.out.println("json string" + jsonString);
        String record = ApiConnection.connectApi(Smsapi,
                "POST", jsonString, "Cookie");
        System.out.println(record);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SMSResponse otpResponse = objectMapper.readValue(record, SMSResponse.class);

        SMSResponse customResponse = new SMSResponse();
        if ("Success".equals(otpResponse.getData().get(0).getMessageErrorDescription())) {
            System.out.println("SMS sent successfully");
            customResponse.setData(otpResponse.getData());
//            return true;
        } else if (otpResponse.getErrorCode() != 0) {
            System.out.println("Error in sending SMS: " + otpResponse.getErrorDescription());
//        return false;
//            customResponse.setData(otpResponse.getData());
        }

        System.out.println("Customized Response: " + otpResponse);
//        return false;
        return otpResponse;
    }


    public Boolean sendEmailResponse(String emailContent, String mailSubject, String emailId) throws JsonMappingException, JsonProcessingException{
        EmailSend emailSend = new EmailSend();
        emailSend.setMailContent(emailContent);
        emailSend.setMailTo(emailId);
        emailSend.setMailSubject(mailSubject);
        String record = ApiConnection.connectApiForMultipart(
                "https://api.apps-external.uat2.phylsys.gov.ph/v1/notifier/email/send",
                emailSend, "POST");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(record);
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        return otpResponse.getResponse().getStatus().equals("success");
    }
//    public Boolean sendSMSResponse(String smsContent) throws JsonMappingException, JsonProcessingException{
//        String Smsapi = environment.getProperty("sms.notifier.url");
//
//        Map<String, Object> jsonData = new HashMap<>();
//        jsonData.put("senderId", "PSA");// check
//        jsonData.put("is_Unicode", true);
//        jsonData.put("is_Flash", false);
//        jsonData.put("schedTime", "");
//        jsonData.put("groupId", "");
//        jsonData.put("message", smsContent);
//
//
//        jsonData.put("mobileNumbers", );
//        jsonData.put("serviceId", "");
//        jsonData.put("coRelator", "");
//        jsonData.put("linkId", "");
//        jsonData.put("principleEntityId", "");
//        jsonData.put("templateId", "");
//        jsonData.put("apiKey", environment.getProperty("otp.api.key"));
//        jsonData.put("clientId", environment.getProperty("otp.client.id"));
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(jsonData);
//        System.out.println("json string" + jsonString);
//        String record = ApiConnection.connectApi(Smsapi,
//                "POST", jsonString, "Cookie");
//        System.out.println(record);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        SMSResponse otpResponse = objectMapper.readValue(record, SMSResponse.class);
//
//        SMSResponse customResponse = new SMSResponse();
//        if ("Success".equals(otpResponse.getData().get(0).getMessageErrorDescription())) {
//            System.out.println("SMS sent successfully");
//            customResponse.setData(otpResponse.getData());
////            return true;
//        } else if (otpResponse.getErrorCode() != 0) {
//            System.out.println("Error in sending SMS: " + otpResponse.getErrorDescription());
////        return false;
////            customResponse.setData(otpResponse.getData());
//        }
//
//        System.out.println("Customized Response: " + otpResponse);
////        return false;
//        return otpResponse;
//    }
//        String record = ApiConnection.connectApiForMultipart(
//                "https://api.apps-external.uat2.phylsys.gov.ph/v1/notifier/email/send",
//                emailSend, "POST");
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(record);
//        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
//        return otpResponse.getResponse().getStatus().equals("success");
//    }


}
