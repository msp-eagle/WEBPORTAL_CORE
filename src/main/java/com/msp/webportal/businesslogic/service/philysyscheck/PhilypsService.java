package com.msp.webportal.businesslogic.service.philysyscheck;

import com.msp.webportal.businesslogic.model.partnerandevice.TSP;
import com.msp.webportal.businesslogic.model.philysyscheck.Philyps;
import com.msp.webportal.businesslogic.model.usecase.Usecase;
import com.msp.webportal.businesslogic.repository.philysyscheck.PhilypsRepo;
import com.msp.webportal.businesslogic.repository.usecase.UsecaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class PhilypsService {

    @Autowired
    private PhilypsRepo philypsRepo;


    public Philyps save(Philyps philyps){
        Philyps save = philypsRepo.save(philyps);
        return save;
    }

    @Transactional
    public List<Philyps> list(){
        List<Philyps> aboutlist = philypsRepo.findAll();
        return aboutlist;
    }

    private Philyps updateDetails(long id, Philyps philyps) {

        Optional<Philyps> optionalUser = philypsRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        Philyps philypss = optionalUser.get();
        philypss.setId(id);
        philypss.setContent(philyps.getContent());
        philypss.setContentTitle(philyps.getContentTitle());
        Philyps modifyAbout = philypsRepo.save(philypss);


        return modifyAbout;
    }

    public Philyps update(long id, Philyps aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        philypsRepo.deleteById(id);
        Optional<Philyps> user = philypsRepo.findById(id);
        return !user.isPresent();
    }

    public Philyps findByid(Long id) {

        Optional<Philyps> optionalUser = philypsRepo.findById(id);
        return optionalUser.get();
    }

}
