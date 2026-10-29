package com.msp.webportal.businesslogic.service.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.about.phisyscouncil.Interagency;
import com.msp.webportal.businesslogic.repository.about.AboutCareersRepository;
import com.msp.webportal.businesslogic.repository.about.phisyscouncil.InteragencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InteragencyService {
@Autowired
private InteragencyRepository interagencyRepository;


    public Interagency save(Interagency aboutDataPrivacy){
        Interagency save = interagencyRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<Interagency> list(){
        List<Interagency> aboutlist = interagencyRepository.findAll();
        return aboutlist;
    }


    public Interagency update(long id, Interagency interagency) {
        return updateDetils(id, interagency);
    }
    private Interagency updateDetils(long id, Interagency interagency) {

        Optional<Interagency> optionalUser = interagencyRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Interagency about = optionalUser.get();
        about.setId(id);
        about.setContent(interagency.getContent());
        about.setContentTitle(interagency.getContentTitle());
        Interagency modifyAbout = interagencyRepository.save(about);


        return modifyAbout;
    }

    public boolean deleteById(Long id) {

        interagencyRepository.deleteById(id);
        Optional<Interagency> user = interagencyRepository.findById(id);
        return !user.isPresent();
    }

    public Interagency findByid(Long id) {

        Optional<Interagency> optionalUser = interagencyRepository.findById(id);
        return optionalUser.get();
    }
}
