package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.Biopartner;
import com.msp.webportal.businesslogic.model.partnerandevice.Devsection;
import com.msp.webportal.businesslogic.model.partnerandevice.Ekyc;
import com.msp.webportal.businesslogic.repository.partneranddevice.DevsecRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.EkyxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevsecService {

    @Autowired
    private DevsecRepo aboutRepository;


    public Devsection save(Devsection about) {
        Devsection save = aboutRepository.save(about);
        return save;
    }

    public List<Devsection> list() {
        List<Devsection> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Devsection updateDetails(long id, Devsection abouts) {

        Optional<Devsection> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        Devsection about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Devsection modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Devsection update(long id, Devsection aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Devsection> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Devsection findByid(Long id) {

        Optional<Devsection> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
