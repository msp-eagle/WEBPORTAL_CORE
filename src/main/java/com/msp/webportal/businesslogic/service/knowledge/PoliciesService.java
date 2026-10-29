package com.msp.webportal.businesslogic.service.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.MediaKno;
import com.msp.webportal.businesslogic.model.knowledge.PoliciesKno;
import com.msp.webportal.businesslogic.repository.knowledge.PoliciesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PoliciesService {
    @Autowired
    private PoliciesRepo policiesRepo;


    public PoliciesKno save(PoliciesKno aboutDataPrivacy){
        PoliciesKno save = policiesRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<PoliciesKno> list(){
        List<PoliciesKno> aboutlist = policiesRepo.findAll();
        return aboutlist;
    }


    private PoliciesKno updateDetails(long id, PoliciesKno abouts) {

        Optional<PoliciesKno> optionalUser = policiesRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        PoliciesKno about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        PoliciesKno modifyAbout = policiesRepo.save(about);


        return modifyAbout;
    }

    public PoliciesKno update(long id, PoliciesKno aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        policiesRepo.deleteById(id);
        Optional<PoliciesKno> user = policiesRepo.findById(id);
        return !user.isPresent();
    }

    public PoliciesKno findByid(Long id) {

        Optional<PoliciesKno> optionalUser = policiesRepo.findById(id);
        return optionalUser.get();
    }
}

