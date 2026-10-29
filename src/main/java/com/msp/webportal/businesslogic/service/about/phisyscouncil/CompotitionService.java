package com.msp.webportal.businesslogic.service.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.phisyscouncil.Compotition;
import com.msp.webportal.businesslogic.repository.about.phisyscouncil.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompotitionService {
    @Autowired
    private CompositionRepository compositionRepository;


    public Compotition save(Compotition aboutDataPrivacy) {
        Compotition save = compositionRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<Compotition> list() {
        List<Compotition> aboutlist = compositionRepository.findAll();
        return aboutlist;
    }

    private Compotition updateDetails(long id, Compotition compotition) {
        Optional<Compotition> optionalUser = compositionRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("Compotition not found");
        }
        Compotition about = optionalUser.get();
        about.setId(id);
        about.setContent(compotition.getContent());
        about.setContentTitle(compotition.getContentTitle());
        Compotition modifyAbout = compositionRepository.save(about);


        return modifyAbout;
    }


    public Compotition update(long id, Compotition abouts) {
        return updateDetails(id, abouts);

    }

    public boolean deleteById(Long id) {

        compositionRepository.deleteById(id);
        Optional<Compotition> user = compositionRepository.findById(id);
        return !user.isPresent();
    }

    public Compotition findByid(Long id) {

        Optional<Compotition> optionalUser = compositionRepository.findById(id);
        return optionalUser.get();

    }
}
