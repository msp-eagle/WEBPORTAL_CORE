package com.msp.webportal.businesslogic.dto.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaRequestDTO implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String captchaToken;
}
