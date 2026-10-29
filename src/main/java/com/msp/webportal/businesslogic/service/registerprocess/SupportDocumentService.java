package com.msp.webportal.businesslogic.service.registerprocess;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.registerprocess.Supportdocument;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.registerprocess.SupportdocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupportDocumentService {
    @Autowired
    private SupportdocumentRepository supportdocumentRepository;


    public Supportdocument save(Supportdocument about){
        Supportdocument save = supportdocumentRepository.save(about);
        return save;
    }

    public List<Supportdocument> list(){
        List<Supportdocument> aboutlist = supportdocumentRepository.findAll();
        return aboutlist;
    }

    public Supportdocument updateDetails(long id, Supportdocument abouts) {

        Optional<Supportdocument> optionalUser = supportdocumentRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Supportdocument about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Supportdocument modifyAbout = supportdocumentRepository.save(about);


        return modifyAbout;
    }

    public Supportdocument update(long id, Supportdocument aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        supportdocumentRepository.deleteById(id);
        Optional<Supportdocument> user = supportdocumentRepository.findById(id);
        return !user.isPresent();
    }

    public Supportdocument findByid(Long id) {

        Optional<Supportdocument> optionalUser = supportdocumentRepository.findById(id);
        return optionalUser.get();
    }
}
