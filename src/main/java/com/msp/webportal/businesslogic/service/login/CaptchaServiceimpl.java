package com.msp.webportal.businesslogic.service.login;


import com.msp.webportal.businesslogic.model.login.Captcha;
import com.msp.webportal.businesslogic.repository.login.CaptchaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CaptchaServiceimpl  {

    @Autowired
    private CaptchaRepo captchaRepo;
    public static final String FILE_TYPE = "jpeg";

    public byte[] generateCaptcha(String ip) {
        String captcha = generateCaptcha(5);
        Captcha captcha2 = new Captcha();
        captcha2.setCaptchaString(captcha);
        captcha2.setDeviceIp(ip);
        captcha2.setActive(true);
        captcha2.setExpiredTime(LocalDateTime.now().plusMinutes(5));
//        System.out.println("captcha2");
        captchaRepo.save(captcha2);
        int width = 150, height = 37;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
        Graphics graphics = bufferedImage.createGraphics();
//        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setFont(new Font("Arial", Font.BOLD, 28));
        graphics.setColor(new Color(19, 0, 90));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString(captcha, 20, 25);
        graphics.drawLine (0, 100, 100, 0);
        graphics.drawLine (0, 50, 50, 0);
        graphics.drawLine (0, 0, 75, 50);
        graphics.drawLine (0, 0, 50, 75);
        graphics.drawLine (0, 110, 110, 0);
        graphics.drawLine (0, 60, 60, 0);
        graphics.drawLine (0, 0, 85, 60);
        graphics.drawLine (0, 0, 60, 85);
        graphics.drawArc (25, 10, 50, 75, 0, 360);
        graphics.drawLine (75, 5, 75, 5);
        graphics.drawLine (5, 50, 5, 50);
        graphics.drawRect (25, 10, 50, 75);
        graphics.fillRect (25, 110, 50, 75);
        graphics.drawRoundRect (100, 10, 50, 75, 60, 50);
        graphics.drawRoundRect (0, 10, 50, 75, 60, 50);
//        graphics.setFont(new Font("Arial", Font.BOLD, 20));
//        graphics.setColor(new Color(65, 65, 173));
//        graphics.fillRect(0, 0, width, height);
//        graphics.setColor(new Color(255, 255, 225));
//        graphics.drawString(captcha, 20, 25);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    private String generateCaptcha(int captchaLength) {
        String captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer captchaBuffer = new StringBuffer();
        Random random = new Random();
        while(captchaBuffer.length() < captchaLength) {
            int index = (int) (random.nextFloat() * captcha.length());
            captchaBuffer.append(captcha.substring(index, index+1));
        }
        return captchaBuffer.toString();
    }

    public boolean verifyCaptcha(String captcha, String ip) {


        Captcha cap = captchaRepo.findTopByDeviceIpOrderByIdDesc(ip);
//        Captcha cap=captchaRepo.findTopByCaptchaStringAndDeviceIp(captcha,ip);
//        System.out.println("ip++"+ip);
//        System.out.println("asd=="+cap);
//        System.out.println(captcha+" "+cap.getCaptchaString());
//        System.out.println("cap.isActive()=="+cap.isActive());

        if(cap != null && captcha.equals(cap.getCaptchaString())  && cap.isActive()) {
//            System.out.println("captcha if checked"+cap.getExpiredTime() +"localtime="+LocalDateTime.now());

//            System.out.println("isBefore="+LocalDateTime.now().isBefore(cap.getExpiredTime()));

            if(LocalDateTime.now().isBefore(cap.getExpiredTime())  || LocalDateTime.now().equals(cap.getExpiredTime()) ) {
//                System.out.println("timechecked");
                cap.setActive(false);
//                System.out.println("ajith"+cap.getCaptchaString());
//                System.out.println("active status"+cap.isActive());
                captchaRepo.save(cap);
                return true;
            }
            return false;
        }
        return false;
    }



}
