package com.msp.webportal.businesslogic.service.sms;


import com.msp.webportal.businesslogic.dto.sms.SmsRequestDto;
import com.msp.webportal.businesslogic.dto.wrapper.RequestWrapper;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

@Service
@AllArgsConstructor
public class SmsNotificationService {
    public boolean sendSms(String bodyValue, Long mobile)  {
        try{
            System.out.println("------- SEND SMS---------"+ bodyValue);
            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
            reactor.netty.http.client.HttpClient httpClient = reactor.netty.http.client.HttpClient.create()
                    .secure(t -> t.sslContext(sslContext));
            WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
            HttpMethod method = HttpMethod.resolve("POST"); // Convert method to HttpMethod
            SmsRequestDto dto = SmsRequestDto.builder().number(mobile).message(bodyValue).build();
            RequestWrapper<SmsRequestDto> requestWrapper = new RequestWrapper<>();
            requestWrapper.setRequest(dto);
            String val = webClient.method(method) // Use the specified HTTP method
                    .uri("https://api.apps-external.uat2.phylsys.gov.ph/v1/sms-gateway/smsutility/otpsms/send")
//                .header("Cookie", "Authorization=" + TOKEN)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .bodyValue(requestWrapper)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println("SMS RESPONE: "+ val);
        }catch (SSLException ex){
            ex.printStackTrace();
        }
        return true;
    }
}
