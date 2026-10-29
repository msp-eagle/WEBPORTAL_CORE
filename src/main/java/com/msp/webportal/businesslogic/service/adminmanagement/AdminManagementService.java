package com.msp.webportal.businesslogic.service.adminmanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.Otp.EmailSend;
import com.msp.webportal.businesslogic.dto.Otp.OtpGenerationResponse;
import com.msp.webportal.businesslogic.dto.Otp.Response;
import com.msp.webportal.businesslogic.model.adminmagement.AdminManager;
import com.msp.webportal.businesslogic.model.adminmagement.Adminlogin;
import com.msp.webportal.businesslogic.repository.adminmanagement.AdminCreationRepo;

import com.msp.webportal.businesslogic.repository.adminmanagement.AdminLoginRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminManagementService {

    @Autowired
    private AdminCreationRepo  adminCreationRepo;

    @Autowired
    private AdminLoginRepo adminLoginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    Environment environment;

//    Password show method

//    public  AdminManager getAllPersons(String email, String password) {
//        AdminManager adminlogin=adminCreationRepo.getallperson(email,password)   ;
//        System.out.println(email+" : "+password);
//        return adminlogin;
//    }

//    password encode method

    public AdminManager getAllPersons(String email, String password) {

        AdminManager adminlogin = adminCreationRepo.findByEmail(email);

        if (adminlogin == null) {
            System.out.println("No admin found with the given email.");
            return null; // Or handle appropriately
        }

        // Retrieve the stored encoded password from the adminlogin object
        String storedEncodedPassword = adminlogin.getPassword();

        // Use passwordEncoder to check if the raw password matches the stored encoded password
        boolean passwordMatches = passwordEncoder.matches(password, storedEncodedPassword);

        if (passwordMatches) {
            System.out.println(email + " : Login successful.");
            return adminlogin;
        } else {
            System.out.println(email + " : Incorrect password.");
            return null;
        }
    }



    public AdminManager save(AdminManager aboutDataPrivacy){
        String encodedPassword = passwordEncoder.encode(aboutDataPrivacy.getPassword());
        aboutDataPrivacy.setPassword(encodedPassword);
        aboutDataPrivacy.setRole("Admin");
        AdminManager save = adminCreationRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<AdminManager> list(){
        List<AdminManager> aboutlist = adminCreationRepo.findAll();
        return aboutlist;
    }


    private AdminManager updateDetails(long id, AdminManager abouts) {

        Optional<AdminManager> optionalUser = adminCreationRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        AdminManager about = optionalUser.get();
        about.setId(id);
        about.setEmail(abouts.getEmail());
        about.setFirstname(abouts.getFirstname());
        about.setLastname(abouts.getLastname());
       // about.setPassword(abouts.getPassword());
        AdminManager modifyAbout = adminCreationRepo.save(about);
        System.out.println(modifyAbout);

        return modifyAbout;
    }

    public AdminManager update(long id, AdminManager aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        adminCreationRepo.deleteById(id);
        Optional<AdminManager> user = adminCreationRepo.findById(id);
        return !user.isPresent();
    }

    public AdminManager findByid(Long id) {

        Optional<AdminManager> optionalUser = adminCreationRepo.findById(id);
        return optionalUser.get();
    }

    public AdminManager findByEmail(String email){
        AdminManager adminManager = adminCreationRepo.findByEmail(email);
        return adminManager;
    }

    public Response otpGenerate(String key) throws JsonProcessingException {
        org.json.JSONObject template = new org.json.JSONObject();
        template.put("id", "string");
        template.put("metadata", new JSONObject()); // Empty object for metadata
        template.put("version", "string");
        JSONObject request = new JSONObject();
        request.put("key", key);
        template.put("request", request);
        String requestOtp = environment.getProperty("otp.manager.request");

        template.put("requesttime", "2018-12-10T06:12:52.994Z");
        String record = ApiConnection.connectApi(requestOtp,
                "POST", template.toString(), "Cookie");
        ObjectMapper objectMapper = new ObjectMapper();
        OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
        return otpResponse.getResponse();
    }

    // public Boolean sendEmail(EmailSend emailSend) throws JsonProcessingException {
    //     System.out.println("sendEmail");
    //     System.out.println(emailSend.toString());
    //     String record = ApiConnection.connectApiForMultipart("https://api.apps-external.uat2.phylsys.gov.ph/v1/notifier/email/send",
    //             emailSend, "POST");
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     System.out.println(record);
    //     OtpGenerationResponse otpResponse = objectMapper.readValue(record, OtpGenerationResponse.class);
    //     return  otpResponse.getResponse().getStatus().equals("success");
    // }
    public String changePassword(AdminManager adminManager){
        AdminManager adminManager1= adminCreationRepo.findByEmail(adminManager.getEmail());
        if(adminManager1 != null){
            if(adminManager.getPassword() !=null){
                adminManager1.setPassword(adminManager.getPassword());
                adminCreationRepo.save(adminManager1);
                return  "SUCCESS";
            }
            else{
                return "Password is empty";
            }
        }
        else{
            return  "Email is invalid";
        }

    }
}
