package com.msp.webportal.businesslogic.dto.resident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordAuthstatusData {
    @JsonProperty("authTypeCode")
    private String authTypeCode;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdDtimes")
    private String createdDtimes;

    @JsonProperty("deletedDtimes")
    private String deletedDtimes;

    @JsonProperty("lockEndDatetime")
    private String lockEndDatetime;

    @JsonProperty("lockRequestDatetime")
    private String lockRequestDatetime;

    @JsonProperty("lockStartDatetime")
    private String lockStartDatetime;

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("updBy")
    private String updBy;

    @JsonProperty("updDtimes")
    private String updDtimes;

    private boolean deleted;
}
