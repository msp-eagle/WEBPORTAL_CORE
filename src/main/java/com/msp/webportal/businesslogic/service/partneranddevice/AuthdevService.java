package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.AuthDevice;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.partneranddevice.AuthdevRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthdevService {

    @Autowired
    private AuthdevRepo aboutRepository;


    public AuthDevice save(AuthDevice about) {
        AuthDevice save = aboutRepository.save(about);
        return save;
    }

    public List<AuthDevice> list() {
        List<AuthDevice> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private AuthDevice updateDetails(long id, AuthDevice abouts) {

        Optional<AuthDevice> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        AuthDevice about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        AuthDevice modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public AuthDevice update(long id, AuthDevice aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<AuthDevice> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public AuthDevice findByid(Long id) {

        Optional<AuthDevice> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
