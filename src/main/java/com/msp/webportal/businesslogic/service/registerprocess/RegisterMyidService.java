package com.msp.webportal.businesslogic.service.registerprocess;

import com.msp.webportal.businesslogic.model.philysyscheck.Philyps;
import com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid;
import com.msp.webportal.businesslogic.model.registerprocess.Registerprocess;
import com.msp.webportal.businesslogic.repository.registerprocess.RegisterMyidRepository;
import com.msp.webportal.businesslogic.repository.registerprocess.RegisterProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RegisterMyidService {

    @Autowired
    private RegisterMyidRepository registerMyidRepository;


    public RegisterMyid save(RegisterMyid aboutDataPrivacy){
        RegisterMyid save = registerMyidRepository.save(aboutDataPrivacy);
        return save;
    }

    public List<RegisterMyid> list(){
        List<RegisterMyid> aboutlist = registerMyidRepository.findAll();
        return aboutlist;
    }


    private RegisterMyid updateDetails(long id, RegisterMyid abouts) {

        Optional<RegisterMyid> optionalUser = registerMyidRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        RegisterMyid about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        RegisterMyid modifyAbout = registerMyidRepository.save(about);


        return modifyAbout;
    }

    public RegisterMyid update(long id, RegisterMyid aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        registerMyidRepository.deleteById(id);
        Optional<RegisterMyid> user = registerMyidRepository.findById(id);
        return !user.isPresent();
    }

    public RegisterMyid findByid(Long id) {

        Optional<RegisterMyid> optionalUser = registerMyidRepository.findById(id);
        return optionalUser.get();
    }
}
