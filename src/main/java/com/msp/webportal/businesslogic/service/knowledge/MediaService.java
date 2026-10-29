package com.msp.webportal.businesslogic.service.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.MediaKno;
import com.msp.webportal.businesslogic.repository.knowledge.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MediaService {
    @Autowired
    private MediaRepo mediaRepo;


    public MediaKno save(MediaKno aboutDataPrivacy){
        MediaKno save = mediaRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<MediaKno> list(){
        List<MediaKno> aboutlist = mediaRepo.findAll();
        return aboutlist;
    }


    private MediaKno updateDetails(long id, MediaKno abouts) {

        Optional<MediaKno> optionalUser = mediaRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        MediaKno about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        MediaKno modifyAbout = mediaRepo.save(about);


        return modifyAbout;
    }

    public MediaKno update(long id, MediaKno aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        mediaRepo.deleteById(id);
        Optional<MediaKno> user = mediaRepo.findById(id);
        return !user.isPresent();
    }

    public MediaKno findByid(Long id) {

        Optional<MediaKno> optionalUser = mediaRepo.findById(id);
        return optionalUser.get();
    }
}

