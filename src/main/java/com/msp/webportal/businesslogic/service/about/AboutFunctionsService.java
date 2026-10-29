package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.AboutFunctions;
import com.msp.webportal.businesslogic.repository.about.AboutFunctionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutFunctionsService {

    @Autowired
    private AboutFunctionsRepo aboutFunctionsRepo;

    public AboutFunctions save(AboutFunctions aboutFunctions){
        AboutFunctions save = aboutFunctionsRepo.save(aboutFunctions);
        return save;
    }

    public List<AboutFunctions> list(){
        List<AboutFunctions> aboutlist = aboutFunctionsRepo.findAll();
        return aboutlist;
    }
}
