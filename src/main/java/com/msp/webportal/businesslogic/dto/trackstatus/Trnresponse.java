package com.msp.webportal.businesslogic.dto.trackstatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trnresponse {
    @JsonProperty("rid_detail")
    private RidDetail ridDetail;
    private Status status;

    @Data
    public static class RidDetail {
        @JsonProperty("PCN_Status")
        private List<PcnStatus> PCN_Status;
        @JsonProperty("error")
        private String Error;
        // Add other fields as necessary
    }

    @Data
    public static class PcnStatus {
        private int serialNumber;
        private String transactionNumber;
        private int statusCode;
        private String statusComment;
        private String createdDateTimes;
        // Add other fields as necessary
    }

    @Data
    public static class Status {
        private String step1;
        private String step2;
        private String step3;
    }
}
