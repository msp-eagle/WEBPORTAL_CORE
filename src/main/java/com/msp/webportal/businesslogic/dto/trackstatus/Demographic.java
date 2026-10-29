package com.msp.webportal.businesslogic.dto.trackstatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Demographic {
    private String presentAddressLine1;
    private String presentAddressLine2;
    private String presentAddressLine3;
    private String presentAddressLine4;
    private String presentZipcode;
    private String presentProvince;
    private String firstname;
    private String birthdate;
    private String birthcity;
    private String modeOfClaim;
    private String permanentBarangay;
    private String psn;
    private String mobileno;
    private String suffix;
    private String introducerFirstName;
    private String bloodtype;
    private String presentBarangay;
    private String registrationId;
    private String residenceStatus;
    private String id;
    private String introducerSuffix;
    private String introducerMiddleName;
    private String email;
    private String birthcountry;
    private String centerId;
    private String permanentZipcode;
    private String sex;
    private String middlename;
    @JsonProperty("BF1")
    private String BF1;
    private String creationDate;
    private String lastname;
    @JsonProperty("BF2")
    private String BF2;
    private String presentCity;
    private String permanentAddressLine1;
    private String permanentAddressLine2;
    private String permanentAddressLine3;
    private String permanentAddressLine4;
    private String permanentCountry;
    private String maritalstatus;
    private String presentCountry;
    private String permanentProvince;
    private String birthprovince;
    private String PCN;
    private String permanentCity;
    private String introducerLastName;
    private String error; // New field to capture error message

}
