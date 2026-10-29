package com.msp.webportal.businesslogic.service.audit;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.audit.AuthLock;
import com.msp.webportal.businesslogic.repository.about.AboutCareersRepository;
import com.msp.webportal.businesslogic.repository.audit.AuthLockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthLockService {


    @Autowired
    private AuthLockRepository authLockRepository;


    public AuthLock save(AuthLock aboutDataPrivacy){
        AuthLock save = authLockRepository.save(aboutDataPrivacy);
        return save;
    }
}
