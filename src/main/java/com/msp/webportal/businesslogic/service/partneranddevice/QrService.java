package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.Operationalmodel;
import com.msp.webportal.businesslogic.model.partnerandevice.Qrcode;
import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.repository.partneranddevice.QrRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QrService {

    @Autowired
    private QrRepo aboutRepository;


    public Qrcode save(Qrcode about) {
        Qrcode save = aboutRepository.save(about);
        return save;
    }

    public List<Qrcode> list() {
        List<Qrcode> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private Qrcode updateDetails(long id, Qrcode abouts) {

        Optional<Qrcode> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        Qrcode about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Qrcode modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public Qrcode update(long id, Qrcode aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<Qrcode> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public Qrcode findByid(Long id) {

        Optional<Qrcode> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }
}
