package com.msp.webportal.businesslogic.service.about;

import com.msp.webportal.businesslogic.model.about.About;
import com.msp.webportal.businesslogic.model.about.AboutNumbers;
import com.msp.webportal.businesslogic.repository.about.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutService {


    @Autowired
    private AboutRepository aboutRepository;


    public About save(About about){
        About save = aboutRepository.save(about);
        return save;
    }

    public List<About> list(){
        List<About> aboutlist = aboutRepository.findAll();
        return aboutlist;
    }

    private About updateDetails(long id, About abouts) {

        Optional<About> optionalUser = aboutRepository.findById(id);
        if(!optionalUser.isPresent()){
        }
        About about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
//        about.setContentTitle(abouts.getContentTitle());

            about.setContentTitle(abouts.getContentTitle());
        About modifyAbout = aboutRepository.save(about);


        return modifyAbout;
    }

    public About update(long id, About aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {
        aboutRepository.deleteById(id);
        Optional<About> user = aboutRepository.findById(id);
        return !user.isPresent();
    }

    public About findByid(Long id) {
        Optional<About> optionalUser = aboutRepository.findById(id);
        System.out.println(optionalUser.get().getContent());
        return optionalUser.get();
    }

}
