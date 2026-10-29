package com.msp.webportal.businesslogic.service.login;

public interface CaptchaService {
    byte[] generateCaptcha(String ip);
    boolean verifyCaptcha(String captcha, String ip);

}
