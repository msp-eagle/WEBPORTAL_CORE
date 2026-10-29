package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.AboutResolutions;
import com.msp.webportal.businesslogic.repository.about.AboutResolutionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutResolutionsService {

    @Autowired
    private AboutResolutionsRepo aboutResolutionsRepo;


    public AboutResolutions save(AboutResolutions aboutResolutions){
        AboutResolutions save = aboutResolutionsRepo.save(aboutResolutions);
        return save;
    }

    public List<AboutResolutions> list(){
        List<AboutResolutions> aboutlist = aboutResolutionsRepo.findAll();
        return aboutlist;
    }
}
