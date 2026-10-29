package com.msp.webportal.businesslogic.service.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.Relyingparty;
import com.msp.webportal.businesslogic.model.partnerandevice.TSP;
import com.msp.webportal.businesslogic.repository.partneranddevice.RpRepo;
import com.msp.webportal.businesslogic.repository.partneranddevice.TSPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TSPService {

    @Autowired
    private TSPRepo aboutRepository;


    public TSP save(TSP about) {
        TSP save = aboutRepository.save(about);
        return save;
    }

    public List<TSP> list() {
        List<TSP> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private TSP updateDetails(long id, TSP abouts) {

        Optional<TSP> optionalUser = aboutRepository.findById(id);
        if (!optionalUser.isPresent()) {
        }
        TSP about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        TSP modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }
    public TSP update(long id, TSP aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }
    public boolean deleteById(Long id) {

        aboutRepository.deleteById(id);
        Optional<TSP> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public TSP findByid(Long id) {

        Optional<TSP> optionalUser = aboutRepository.findById(id);
        return optionalUser.get();
    }

}
