package com.msp.webportal.businesslogic.service.about;

import com.msp.webportal.businesslogic.model.about.Transparancy;
import com.msp.webportal.businesslogic.repository.about.TransparancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutTranceparancyService {

    @Autowired
    private TransparancyRepository transparancyRepository;


    public Transparancy save(Transparancy about) {
        System.out.println("About" + about);
        Transparancy save = transparancyRepository.save(about);
        return save;
    }

    public List<Transparancy> list() {
        List<Transparancy> aboutlist = transparancyRepository.findAll();
        return aboutlist;
    }

    private Transparancy updateDetails(long id, Transparancy abouts) {

        Optional<Transparancy> optionalUser = transparancyRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        Transparancy about = optionalUser.get();
        about.setId(id);
        about.setContentTitle(abouts.getContentTitle());
        about.setContent(abouts.getContent());

        Transparancy modifyAbout = transparancyRepository.save(about);


        return modifyAbout;
    }


    public Transparancy update(long id, Transparancy aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        transparancyRepository.deleteById(id);
        Optional<Transparancy> user = transparancyRepository.findById(id);
        return !user.isPresent();
    }

    public Transparancy findByid(Long id) {

        Optional<Transparancy> optionalUser = transparancyRepository.findById(id);
        return optionalUser.get();
    }
}


