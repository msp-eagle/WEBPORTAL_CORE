package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.AuthDevice;
import com.msp.webportal.businesslogic.model.partnerandevice.AuthDocument;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.partneranddevice.AuthdocsRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthdocsService {

    @Autowired
    private AuthdocsRepo aboutRepository;


    public AuthDocument save(AuthDocument about) {
        AuthDocument save = aboutRepository.save(about);
        return save;
    }

    public List<AuthDocument> list() {
        List<AuthDocument> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private AuthDocument updateDetails(long id, AuthDocument abouts) {

        Optional<AuthDocument> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        AuthDocument about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        AuthDocument modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public AuthDocument update(long id, AuthDocument aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }


    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<AuthDocument> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public AuthDocument findByid(Long id) {

        Optional<AuthDocument> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
