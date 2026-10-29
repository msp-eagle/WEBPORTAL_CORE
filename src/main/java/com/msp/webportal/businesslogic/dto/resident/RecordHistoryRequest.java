package com.msp.webportal.businesslogic.dto.resident;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordHistoryRequest {
    private String authTypeCode;
    private String fromDate;
    private String individualId;
    private String individualIdType;
    private String toDate;
}
