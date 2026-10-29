package com.msp.webportal.businesslogic.dto.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sms {

        private String senderId;
        private String is_Unicode;
        private String is_Flash;
        private String schedTime;
        private String groupId;
        private String message;
        private Integer mobileNumbers;
        private String serviceId;
        private String coRelator;
        private String linkId;
        private String principleEntityId;
        private String templateId;
        private String apiKey;
        private String clientId;

}
