package com.msp.webportal.businesslogic.commonUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean("CustomWebClient")
    public WebClient webClientConfig(){
        return WebClient.builder().build();
    }
}
