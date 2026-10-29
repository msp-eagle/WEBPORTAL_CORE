package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.about.AboutDataPrivacy;
import com.msp.webportal.businesslogic.model.about.AboutLegal;
import com.msp.webportal.businesslogic.repository.about.AboutLegalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutLegalService {



    @Autowired
    private AboutLegalRepo aboutLegalRepo;


    public AboutLegal save(AboutLegal aboutLegal){
        AboutLegal save = aboutLegalRepo.save(aboutLegal);
        return save;
    }

    public List<AboutLegal> list(){
        List<AboutLegal> aboutLegallist = aboutLegalRepo.findAll();
        return aboutLegallist;
    }


    private AboutLegal updateDetails(long id, AboutLegal abouts) {

        Optional<AboutLegal> optionalUser = aboutLegalRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        AboutLegal about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        AboutLegal modifyAbout = aboutLegalRepo.save(about);
        return modifyAbout;
    }


    public AboutLegal update(long id, AboutLegal aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }


    public boolean deleteById(Long id) {
        aboutLegalRepo.deleteById(id);
        Optional<AboutLegal> user = aboutLegalRepo.findById(id);
        return !user.isPresent();
    }

    public AboutLegal findByid(Long id) {
        Optional<AboutLegal> optionalUser = aboutLegalRepo.findById(id);
        return optionalUser.get();
    }
}
