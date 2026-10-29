package com.msp.webportal.businesslogic.service.about;


import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.about.AboutDataPrivacy;
import com.msp.webportal.businesslogic.model.about.AboutNumbers;
import com.msp.webportal.businesslogic.repository.about.AboutNumbersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutNumbersService {

    @Autowired
    private AboutNumbersRepo aboutNumbersRepo;


    public AboutNumbers save(AboutNumbers aboutNumbers){
        AboutNumbers save = aboutNumbersRepo.save(aboutNumbers);
        return save;
    }

    public List<AboutNumbers> list(){
        List<AboutNumbers> aboutlist = aboutNumbersRepo.findAll();
        return aboutlist;
    }



    private AboutNumbers updateDetails(long id, AboutNumbers abouts) {

        Optional<AboutNumbers> optionalUser = aboutNumbersRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        AboutNumbers about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        AboutNumbers modifyAbout = aboutNumbersRepo.save(about);


        return modifyAbout;
    }


    public AboutNumbers update(long id, AboutNumbers aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }






    public boolean deleteById(Long id) {

        aboutNumbersRepo.deleteById(id);
        Optional<AboutNumbers> user = aboutNumbersRepo.findById(id);
        return !user.isPresent();
    }




    public AboutNumbers findByid(Long id) {

        Optional<AboutNumbers> optionalUser = aboutNumbersRepo.findById(id);
        return optionalUser.get();
    }

}
