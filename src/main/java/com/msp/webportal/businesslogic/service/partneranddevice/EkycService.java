package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.Ekyc;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.partneranddevice.EkyxRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EkycService {

    @Autowired
    private EkyxRepo aboutRepository;


    public Ekyc save(Ekyc about) {
        Ekyc save = aboutRepository.save(about);
        return save;
    }

    public List<Ekyc> list() {
        List<Ekyc> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Ekyc updateDetails(long id, Ekyc abouts) {

        Optional<Ekyc> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        Ekyc about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Ekyc modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Ekyc update(long id, Ekyc aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Ekyc> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Ekyc findByid(Long id) {

        Optional<Ekyc> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
