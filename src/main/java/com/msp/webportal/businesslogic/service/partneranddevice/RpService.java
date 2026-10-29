package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RpService {


    @Autowired
    private RpRepo aboutRepository;


    public Relyingparty save(Relyingparty about) {
        Relyingparty save = aboutRepository.save(about);
        return save;
    }



    @Transactional
    public List<Relyingparty> list() {
        List<Relyingparty> aboutList = aboutRepository.findAll();

//        // Debug log the retrieved content for testing
//        aboutList.forEach(rp -> {
//            if (rp.getContent() != null) {
////                System.out.println("Retrieved content of length: " + rp.getContent().length());
//            }
//        });

        return aboutList;
    }
//    @Transactional
//    public List<Relyingparty> list() {
//        List<Relyingparty> aboutlist = aboutRepository.findAll();
//        return aboutlist;
//    }

    private Relyingparty updateDetails(long id, Relyingparty abouts) {

        Optional<Relyingparty> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        Relyingparty about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Relyingparty modifyAbout = aboutRepository.save(about);

        return modifyAbout;
    }

    public Relyingparty update(long id, Relyingparty aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Relyingparty> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Relyingparty findByid(Long id) {

        Optional<Relyingparty> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}