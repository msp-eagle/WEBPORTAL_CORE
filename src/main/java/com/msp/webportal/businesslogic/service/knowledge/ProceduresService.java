package com.msp.webportal.businesslogic.service.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.ProceduresKno;
import com.msp.webportal.businesslogic.repository.knowledge.ProceduresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProceduresService {
    @Autowired
    private ProceduresRepo proceduresRepo;


    public ProceduresKno save(ProceduresKno aboutDataPrivacy){
        ProceduresKno save = proceduresRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<ProceduresKno> list(){
        List<ProceduresKno> aboutlist = proceduresRepo.findAll();
        return aboutlist;
    }


    private ProceduresKno updateDetails(long id, ProceduresKno abouts) {

        Optional<ProceduresKno> optionalUser = proceduresRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        ProceduresKno about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        ProceduresKno modifyAbout = proceduresRepo.save(about);


        return modifyAbout;
    }

    public ProceduresKno update(long id, ProceduresKno aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        proceduresRepo.deleteById(id);
        Optional<ProceduresKno> user = proceduresRepo.findById(id);
        return !user.isPresent();
    }

    public ProceduresKno findByid(Long id) {

        Optional<ProceduresKno> optionalUser = proceduresRepo.findById(id);
        return optionalUser.get();
    }
}

