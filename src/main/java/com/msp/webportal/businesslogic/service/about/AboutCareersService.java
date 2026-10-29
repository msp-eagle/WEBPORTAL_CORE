package com.msp.webportal.businesslogic.service.about;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.about.AboutDataPrivacy;
import com.msp.webportal.businesslogic.repository.about.AboutCareersRepository;
import com.msp.webportal.businesslogic.repository.about.AboutDataPrivacyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class AboutCareersService {
    @Autowired
    private AboutCareersRepository careersRepository;


    public AboutCareers save(AboutCareers aboutDataPrivacy){
        AboutCareers save = careersRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<AboutCareers> list(){
        List<AboutCareers> aboutlist = careersRepository.findAll();
        return aboutlist;
    }

    public AboutCareers update(long id, AboutCareers abouts) {
        Optional<AboutCareers> optionalUser = careersRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new EntityNotFoundException("User not found");
        }
        return updateAbout(id,abouts);
    }


    private AboutCareers updateAbout(long id,AboutCareers aboutDataPrivacy){
        Optional<AboutCareers> optionalUser = careersRepository.findById(id);
        AboutCareers about = optionalUser.get();
        about.setId(id);
        about.setContent(aboutDataPrivacy.getContent());
        about.setContentTitle(aboutDataPrivacy.getContentTitle());

        AboutCareers modifyAbout = careersRepository.save(about);
        return modifyAbout;
    }

    public boolean deleteById(Long id) {
        careersRepository.deleteById(id);
        Optional<AboutCareers> user = careersRepository.findById(id);
        return !user.isPresent();
    }

    public AboutCareers findByid(Long id) {
        Optional<AboutCareers> optionalUser = careersRepository.findById(id);
        return optionalUser.get();
    }
}
