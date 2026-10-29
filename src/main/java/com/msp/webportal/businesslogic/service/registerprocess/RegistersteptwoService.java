package com.msp.webportal.businesslogic.service.registerprocess;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.registerprocess.RegistersteptwoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class RegistersteptwoService {
    @Autowired
    private RegistersteptwoRepository registersteptwoRepository;


    public Registersteptwo save(Registersteptwo about){
        Registersteptwo save = registersteptwoRepository.save(about);
        return save;
    }

    public List<Registersteptwo> list(){
        List<Registersteptwo> aboutlist = registersteptwoRepository.findAll();
        return aboutlist;
    }

    private Registersteptwo updateDetails(long id, Registersteptwo abouts) {

        Optional<Registersteptwo> optionalUser = registersteptwoRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Registersteptwo about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Registersteptwo modifyAbout = registersteptwoRepository.save(about);


        return modifyAbout;
    }

    public Registersteptwo update(long id, Registersteptwo aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        registersteptwoRepository.deleteById(id);
        Optional<Registersteptwo> user = registersteptwoRepository.findById(id);
        return !user.isPresent();
    }

    public Registersteptwo findByid(Long id) {

        Optional<Registersteptwo> optionalUser = registersteptwoRepository.findById(id);
        return optionalUser.get();
    }
}
