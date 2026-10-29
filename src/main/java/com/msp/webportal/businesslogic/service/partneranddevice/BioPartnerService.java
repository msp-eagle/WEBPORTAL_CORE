package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.partnerandevice.Biopartner;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.partneranddevice.BiopartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BioPartnerService {


    @Autowired
    private BiopartnerRepo aboutRepository;


    public Biopartner save(Biopartner about){
        Biopartner save = aboutRepository.save(about);
        return save;
    }

    public List<Biopartner> list(){
        List<Biopartner> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Biopartner updateDetails(long id, Biopartner abouts) {

        Optional<Biopartner> optionalUser = aboutRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Biopartner about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Biopartner modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Biopartner update(long id, Biopartner aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Biopartner> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Biopartner findByid(Long id) {

        Optional<Biopartner> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
