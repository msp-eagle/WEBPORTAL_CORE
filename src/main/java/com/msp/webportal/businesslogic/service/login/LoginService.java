
package com.msp.webportal.businesslogic.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msp.webportal.businesslogic.commonUtil.ApiConnection;
import com.msp.webportal.businesslogic.dto.login.PcnDobRequestDto;
import com.msp.webportal.businesslogic.dto.login.PcnDobResponseDto;
import com.msp.webportal.businesslogic.dto.trackstatus.Demographic;
import com.msp.webportal.businesslogic.model.login.PcnDobAttemptLog;
import com.msp.webportal.businesslogic.model.login.User;
import com.msp.webportal.businesslogic.repository.login.LoginRepository;
import com.msp.webportal.businesslogic.repository.login.PcnDobAttemptLogRepository;
import com.msp.webportal.businesslogic.response.CustomResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final LoginRepository loginRepository;

    private final Environment environment;

    private final PcnDobAttemptLogRepository pcnDobAttemptLogRepository;

    @Qualifier("CustomWebClient")
    private final WebClient webClient;

    public User addNewUser(User user) throws NoSuchAlgorithmException {
        String encpassword = new MacAddress().md5Encode(user.getPassword().getBytes());
        System.out.println("Password :"+encpassword);
        System.out.println(encpassword);
        user.setPassword(encpassword);
        user.setConfirmPassword(encpassword);
        LocalDateTime cr_dtime = LocalDateTime.now();
        user.setLoggeddate(cr_dtime);
        user.setStatus("1");
        if (!loginRepository.existsByPcn(user.getPcn())){
            return loginRepository.save(user);
        }

        return null;
    }

    public void updateLastLoginTime(String pcn) {
        loginRepository.updateLastLoginTime(pcn, LocalDateTime.now());
    }


    public User getAllPersons(String pcn, String pwd) {
        User userList= loginRepository.getAllPersons(pcn ,pwd);
        System.out.printf("CHECK WHETHER THE PCN"+ loginRepository.existsByPcn(pcn));
        return userList;
    }
    public LocalDateTime getLoggedDateByPcn(String pcn) {
        return loginRepository.getLoggedDateByPcn(pcn);
    }

    public int changepwd(String newpwd, String pcn) throws NoSuchAlgorithmException {
        setFailureCountToZero(pcn);
        return loginRepository.changepwdmail( newpwd, pcn );
    }


    public Demographic demorequest(String pcn) throws JsonMappingException, JsonProcessingException {
        String trnsts = environment.getProperty("demo.request.url");
        String template = environment.getProperty("template");
        String url = trnsts + "/" + pcn;
        String record = ApiConnection.connectApi(url,
                "GET", "", "Cookie");
        System.out.println("records"+ record);
        ObjectMapper objectMapper = new ObjectMapper();
        Demographic demoresponse = objectMapper.readValue(record, Demographic.class);

        return demoresponse;
    }

    public void increaseFailureCount(String pcn){
        Optional<User> user = Optional.ofNullable(loginRepository.findByPcn(pcn));
        if(user.isPresent()){
            user.get().setFailureCount(user.get().getFailureCount() + 1);
            if(user.get().getFailureCount() == 5){
                user.get().setFailureCountExpiredAt(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
            }
            loginRepository.save(user.get());
        }
    }

    public boolean hasExceededMaxLoginAttempts(String pcn) {
        Optional<User> user = Optional.ofNullable(loginRepository.findByPcn(pcn));
    if(user.isPresent()){
        if(user.get().getFailureCount() == null){
            user.get().setFailureCount(0);
            loginRepository.save(user.get());
            return false;
        }
        else if (user.get().getFailureCount() >= 5) {
            if (LocalDateTime.now().isAfter(user.get().getFailureCountExpiredAt())) {
                user.get().setFailureCount(0);
                loginRepository.save(user.get());
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    else {
        return false;
    }


    }

    public CustomResponse pcnAndDobIsValid(PcnDobRequestDto dto){
        CustomResponse response = new CustomResponse();
        try {
            String url = "https://api.apps-external.uat2.phylsys.gov.ph/MAPP/v1/login/PCN";

            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE) // For demo purposes, avoid this in production
                    .build();

            HttpClient httpClient = HttpClient.create()
                    .secure(t -> t.sslContext(sslContext));

            WebClient webClient = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient)) // Use custom HttpClient with SSL
                    .build();

            PcnDobResponseDto pcnDobResponseDto = webClient.post()
                    .uri(url)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(PcnDobResponseDto.class)
                    .block();
            if(pcnDobResponseDto != null){
                if(pcnDobResponseDto.getError() == null){
                    response.setStatus("Success");
                    response.setData(pcnDobResponseDto);
                }
                else{
                    increasePcnDobFailureCount(dto.getPcn());
                    response.setStatus("Failed");
                    response.setError(pcnDobResponseDto.getErrorMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }
    public void increasePcnDobFailureCount(String pcn){
        Optional<PcnDobAttemptLog> log = Optional.ofNullable(pcnDobAttemptLogRepository.findByPcn(pcn));
        if(log.isPresent()){
            log.get().setFailureCount(log.get().getFailureCount() + 1);
            if(log.get().getFailureCount() == 5){
                log.get().setFailureCountExpiredAt(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
            }
            pcnDobAttemptLogRepository.save(log.get());
        }
    }

    public void setFailureCountToZero(String pcn){
        Optional<PcnDobAttemptLog> log = Optional.ofNullable(pcnDobAttemptLogRepository.findByPcn(pcn));
        if(log.isPresent()){
            log.get().setFailureCount(0);
            pcnDobAttemptLogRepository.save(log.get());
        }
    }

    public boolean hasExceededMaxPcnDobAttempts(String pcn) {
        Optional<PcnDobAttemptLog> log = Optional.ofNullable(pcnDobAttemptLogRepository.findByPcn(pcn));
        if (log.isPresent()) {
            if (log.get().getFailureCount() >= 5) {
                if (LocalDateTime.now().isAfter(log.get().getFailureCountExpiredAt())) {
                    log.get().setFailureCount(0);
                    pcnDobAttemptLogRepository.save(log.get());
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            PcnDobAttemptLog pcnDobAttemptLog = PcnDobAttemptLog.builder().failureCount(1).pcn(pcn)
                    .failureCountExpiredAt(LocalDateTime.now()).pcn(pcn).build();
            pcnDobAttemptLogRepository.save(pcnDobAttemptLog);
            return false;
        }

    }

}






//package com.service.philyps.service.login;
//
//import com.service.philyps.controller.login.MacAddress;
//import com.service.philyps.model.login.User;
//import com.service.philyps.repository.login.LoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//
//@Service
//public class LoginService {
//
//    @Autowired
//    LoginRepository loginRepository;
//
//
//    public User addNewUser(User user) throws NoSuchAlgorithmException {
//        String encpassword = new MacAddress().md5Encode(user.getPassword().getBytes());
//        System.out.println("Password :"+encpassword);
//        user.setPassword(encpassword);
//        LocalDateTime cr_dtime = LocalDateTime.now();
//        user.setStatus("1");
//        return loginRepository.save(user);
//    }
//
//
//
//
//    public User getAllPersons(String pcn, String pwd) {
//        User userList= loginRepository.getAllPersons(pcn ,pwd);
//        return userList;
//    }
//
//    public int changepwd(String newpwd, String pcn) throws NoSuchAlgorithmException {
//
//        String encpassword = new MacAddress().md5Encode(newpwd.getBytes());
//        return loginRepository.changepwdmail( encpassword, pcn );
//    }
//
//
//}
//
//
