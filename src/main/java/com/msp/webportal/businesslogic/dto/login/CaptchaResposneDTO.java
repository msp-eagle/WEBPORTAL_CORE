package com.msp.webportal.businesslogic.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaResposneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("success")
	private boolean success;
	
	@JsonProperty("message")
	private String message;

}
