package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.AuthDocument;
import com.msp.webportal.businesslogic.model.partnerandevice.BioDevice;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.partneranddevice.BiodevRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiodevService {

    @Autowired
    private BiodevRepo aboutRepository;


    public BioDevice save(BioDevice about) {
        BioDevice save = aboutRepository.save(about);
        return save;
    }

    public List<BioDevice> list() {
        List<BioDevice> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private BioDevice updateDetails(long id, BioDevice abouts) {

        Optional<BioDevice> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        BioDevice about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        BioDevice modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public BioDevice update(long id, BioDevice aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<BioDevice> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public BioDevice findByid(Long id) {

        Optional<BioDevice> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
