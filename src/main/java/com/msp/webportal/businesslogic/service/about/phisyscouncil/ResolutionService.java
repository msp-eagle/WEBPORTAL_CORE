package com.msp.webportal.businesslogic.service.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.phisyscouncil.Resolution;
import com.msp.webportal.businesslogic.repository.about.phisyscouncil.ResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ResolutionService {

    @Autowired
    private ResolutionRepository resolutionRepository;


    public Resolution save(Resolution aboutDataPrivacy){
        Resolution save = resolutionRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<Resolution> list(){
        List<Resolution> aboutlist = resolutionRepository.findAll();
        return aboutlist;
    }


    public Resolution update(long id, Resolution resolution) {
        return updateDetails(id, resolution);
    }

    private Resolution updateDetails(long id, Resolution resolution) {

        Optional<Resolution> optionalUser = resolutionRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Resolution about = optionalUser.get();
        about.setId(id);
        about.setContent(resolution.getContent());
        about.setContentTitle(resolution.getContentTitle());
        Resolution modifyAbout = resolutionRepository.save(about);


        return modifyAbout;
    }

    public boolean deleteById(Long id) {

        resolutionRepository.deleteById(id);
        Optional<Resolution> user = resolutionRepository.findById(id);
        return !user.isPresent();
    }

    public Resolution findByid(Long id) {

        Optional<Resolution> optionalUser = resolutionRepository.findById(id);
        return optionalUser.get();
    }
}
