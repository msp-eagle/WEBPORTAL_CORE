package com.msp.webportal.businesslogic.service.registerprocess;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.registerprocess.Registerprocess;
import com.msp.webportal.businesslogic.repository.about.AboutCareersRepository;
import com.msp.webportal.businesslogic.repository.registerprocess.RegisterProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RegisterProcessService {


    @Autowired
    private RegisterProcessRepository registerProcessRepository;


    public Registerprocess save(Registerprocess aboutDataPrivacy){
        Registerprocess save = registerProcessRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<Registerprocess> list(){
        List<Registerprocess> aboutlist = registerProcessRepository.findAll();
        return aboutlist;
    }


    private Registerprocess updateDetails(long id, Registerprocess abouts) {

        Optional<Registerprocess> optionalUser = registerProcessRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        Registerprocess about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        Registerprocess modifyAbout = registerProcessRepository.save(about);


        return modifyAbout;
    }
    public Registerprocess update(long id, Registerprocess aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        registerProcessRepository.deleteById(id);
        Optional<Registerprocess> user = registerProcessRepository.findById(id);
        return !user.isPresent();
    }

    public Registerprocess findByid(Long id) {

        Optional<Registerprocess> optionalUser = registerProcessRepository.findById(id);
        return optionalUser.get();
    }
}
