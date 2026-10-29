package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.AboutInterAgency;
import com.msp.webportal.businesslogic.repository.about.AboutInterAgencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutInterAgencyService {

    @Autowired
    private AboutInterAgencyRepo aboutInterAgencyRepo;

    public AboutInterAgency save(AboutInterAgency aboutInterAgency){
        AboutInterAgency save = aboutInterAgencyRepo.save(aboutInterAgency);
        return save;
    }

    public List<AboutInterAgency> list(){
        List<AboutInterAgency> aboutlist = aboutInterAgencyRepo.findAll();
        return aboutlist;
    }
}
