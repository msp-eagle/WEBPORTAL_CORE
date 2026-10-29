package com.msp.webportal.businesslogic.repository.login;

import com.msp.webportal.businesslogic.model.login.Captcha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaptchaRepo extends CrudRepository<Captcha,String> {
    Captcha findTopByDeviceIpOrderByIdDesc(String ip);

    Captcha findTopByCaptchaStringAndDeviceIp(String captcha,String ip);





}