package com.msp.webportal.businesslogic.dto.crms;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ZohoTicket {
    private String name;
    private String email;
    private String contactId;
    private Contact contact;
    private String category;
    private String subject;
    private String description;
    private String departmentId;
    private String pcn;

    @Data
    @Builder
    public static class Contact {
        // private String firstName;
        private String lastName;
        // private String phone;
        private String email;
    }
}
