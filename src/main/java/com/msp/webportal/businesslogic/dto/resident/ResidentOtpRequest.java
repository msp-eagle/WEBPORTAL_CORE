package com.msp.webportal.businesslogic.dto.resident;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import org.hibernate.boot.Metadata;

import java.util.List;

@Data
@Builder
public class ResidentOtpRequest {
    private String id;
    private String individualId;
    private String individualIdType;
    private Metadata metadata;
    private List<String> otpChannel;
    private String requestTime;
    private String transactionID;
    private String version;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
