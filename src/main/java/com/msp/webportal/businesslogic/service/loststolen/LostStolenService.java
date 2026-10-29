package com.msp.webportal.businesslogic.service.loststolen;

import com.msp.webportal.businesslogic.model.login.User;
import com.msp.webportal.businesslogic.model.loststolen.Loststolen;
import com.msp.webportal.businesslogic.repository.login.LoginRepository;
import com.msp.webportal.businesslogic.repository.loststolen.LostStolenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LostStolenService {


    @Autowired
    LostStolenRepository lostStolenRepository;


    public Loststolen lostcard(Loststolen about) {
        LocalDateTime cr_dtime = LocalDateTime.now();
        about.setCr_dTime(cr_dtime);
        about.setModel("LOSTUIN");
        about.setPcn(about.getPcn());
        about.setCard(about.getCard());
        about.setReason(about.getReason());
        return lostStolenRepository.save(about);
    }
}
