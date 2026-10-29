package com.msp.webportal.businesslogic.service.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.about.phisyscouncil.Function;
import com.msp.webportal.businesslogic.repository.about.AboutCareersRepository;
import com.msp.webportal.businesslogic.repository.about.phisyscouncil.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FunctionService {
    @Autowired
    private FunctionRepository functionRepository;


    public Function save(Function aboutDataPrivacy){
        Function save = functionRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<Function> list(){
        List<Function> aboutlist = functionRepository.findAll();
        return aboutlist;
    }

    private Function updateDetails(long id, Function function) {

        Optional<Function> optionalUser = functionRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Function about = optionalUser.get();
        about.setId(id);
        about.setContent(function.getContent());
        about.setContentTitle(function.getContentTitle());
        Function modifyAbout = functionRepository.save(about);


        return modifyAbout;
    }

    public Function update(long id, Function function) {
       return updateDetails(id, function);
    }

    public boolean deleteById(Long id) {

        functionRepository.deleteById(id);
        Optional<Function> user = functionRepository.findById(id);
        return !user.isPresent();
    }

    public Function findByid(Long id) {

        Optional<Function> optionalUser = functionRepository.findById(id);
        return optionalUser.get();
    }
}
