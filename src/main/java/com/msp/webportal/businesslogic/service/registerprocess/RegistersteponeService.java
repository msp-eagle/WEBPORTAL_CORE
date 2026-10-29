package com.msp.webportal.businesslogic.service.registerprocess;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.registerprocess.Registersterpone;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.registerprocess.RegistersteponeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class RegistersteponeService {
    @Autowired
    private RegistersteponeRepository registersteponeRepository;


    public Registersterpone save(Registersterpone about){
        Registersterpone save = registersteponeRepository.save(about);
        return save;
    }

    public List<Registersterpone> list(){
        List<Registersterpone> aboutlist = registersteponeRepository.findAll();
        return aboutlist;
    }

    private Registersterpone updateDetails(long id, Registersterpone abouts) {
        Optional<Registersterpone> optionalUser = registersteponeRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Registersterpone about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        Registersterpone modifyAbout = registersteponeRepository.save(about);

        return modifyAbout;
    }

    public Registersterpone update(long id, Registersterpone aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        registersteponeRepository.deleteById(id);
        Optional<Registersterpone> user = registersteponeRepository.findById(id);
        return !user.isPresent();
    }

    public Registersterpone findByid(Long id) {

        Optional<Registersterpone> optionalUser = registersteponeRepository.findById(id);
        return optionalUser.get();
    }
}
