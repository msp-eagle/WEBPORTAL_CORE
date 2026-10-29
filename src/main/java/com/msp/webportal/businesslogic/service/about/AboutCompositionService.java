package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.AboutComposition;
import com.msp.webportal.businesslogic.repository.about.AboutCompositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutCompositionService {

    @Autowired
    private AboutCompositionRepo aboutCompositionRepo;


    public AboutComposition save(AboutComposition aboutComposition){
        AboutComposition save = aboutCompositionRepo.save(aboutComposition);
        return save;
    }

    public List<AboutComposition> list(){
        List<AboutComposition> aboutlist = aboutCompositionRepo.findAll();
        return aboutlist;
    }
}
