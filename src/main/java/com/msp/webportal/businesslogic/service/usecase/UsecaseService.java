package com.msp.webportal.businesslogic.service.usecase;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.usecase.Usecase;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import com.msp.webportal.businesslogic.repository.usecase.UsecaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsecaseService {

    @Autowired
    private UsecaseRepo usecaseRepo;


    public Usecase save(Usecase usecase){
        Usecase save = usecaseRepo.save(usecase);
        return save;
    }

    public List<Usecase> list(){
        List<Usecase> aboutlist = usecaseRepo.findAll();
        return aboutlist;
    }

    public Usecase update(long id, Usecase usecases) {

        Optional<Usecase> optionalUser = usecaseRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        Usecase usecase = optionalUser.get();
        usecase.setId(id);
        usecase.setContent(usecases.getContent());
        usecase.setContentTitle(usecases.getContentTitle());
        Usecase modifyAbout = usecaseRepo.save(usecase);


        return modifyAbout;
    }

    public boolean deleteById(Long id) {

        usecaseRepo.deleteById(id);
        Optional<Usecase> user = usecaseRepo.findById(id);
        return !user.isPresent();
    }

    public Usecase findByid(Long id) {

        Optional<Usecase> optionalUser = usecaseRepo.findById(id);
        return optionalUser.get();
    }
}
