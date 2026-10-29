package com.msp.webportal.businesslogic.service.about;



import com.msp.webportal.businesslogic.model.about.AboutDataPrivacy;
import com.msp.webportal.businesslogic.repository.about.AboutDataPrivacyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutDataprivacyService {

    @Autowired
    private AboutDataPrivacyRepo aboutDataPrivacyRepo;


    public AboutDataPrivacy save(AboutDataPrivacy aboutDataPrivacy){
        AboutDataPrivacy save = aboutDataPrivacyRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<AboutDataPrivacy> list(){
        List<AboutDataPrivacy> aboutlist = aboutDataPrivacyRepo.findAll();
        return aboutlist;
    }


    private AboutDataPrivacy updateDetails(long id, AboutDataPrivacy aboutDataPrivacy) {

        Optional<AboutDataPrivacy> optionalUser = aboutDataPrivacyRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        AboutDataPrivacy about = optionalUser.get();
        about.setId(id);
        about.setContent(aboutDataPrivacy.getContent());
        about.setContentTitle(aboutDataPrivacy.getContentTitle());
        AboutDataPrivacy modifyAbout = aboutDataPrivacyRepo.save(about);


        return modifyAbout;
    }

    public AboutDataPrivacy update(long id,AboutDataPrivacy aboutDataPrivacy) {
      return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutDataPrivacyRepo.deleteById(id);
        Optional<AboutDataPrivacy> user = aboutDataPrivacyRepo.findById(id);
        return !user.isPresent();
    }

    public AboutDataPrivacy findByid(Long id) {

        Optional<AboutDataPrivacy> optionalUser = aboutDataPrivacyRepo.findById(id);
        return optionalUser.get();
    }
}
