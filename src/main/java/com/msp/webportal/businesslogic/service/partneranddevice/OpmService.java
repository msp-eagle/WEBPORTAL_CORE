package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.partnerandevice.Operationalmodel;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.partneranddevice.OpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpmService {


    @Autowired
    private OpRepo aboutRepository;


    public Operationalmodel save(Operationalmodel about){
        Operationalmodel save = aboutRepository.save(about);
        return save;
    }

    public List<Operationalmodel> list(){
        List<Operationalmodel> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Operationalmodel updateDetails(long id, Operationalmodel abouts) {

        Optional<Operationalmodel> optionalUser = aboutRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Operationalmodel about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Operationalmodel modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Operationalmodel update(long id, Operationalmodel aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Operationalmodel> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Operationalmodel findByid(Long id) {

        Optional<Operationalmodel> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
