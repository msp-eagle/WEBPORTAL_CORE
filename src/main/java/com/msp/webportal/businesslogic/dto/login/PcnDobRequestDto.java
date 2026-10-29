package com.msp.webportal.businesslogic.dto.login;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PcnDobRequestDto {

    @NotBlank(message = "Pcn should not be blank")
    private String pcn;
@NotBlank(message = "DOB should not be blank")
    private String dob;

}
