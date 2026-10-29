package com.msp.webportal.businesslogic.dto.trackstatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VidInfo {
    private String vid;
    private String vidType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private String expiryTimestamp;
    private Object transactionLimit;
    private HashAttributes hashAttributes;
    public VidInfo() {
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class HashAttributes {
        private String SALT;
        private String MODULO;
        private String id_hash;
        public HashAttributes() {
        }
    }

}
