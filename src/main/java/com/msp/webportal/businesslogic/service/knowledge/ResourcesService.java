package com.msp.webportal.businesslogic.service.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.ResourcesKno;
import com.msp.webportal.businesslogic.repository.knowledge.ResourcesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ResourcesService {
    @Autowired
    private ResourcesRepo resourcesRepo;


    public ResourcesKno save(ResourcesKno aboutDataPrivacy){
        ResourcesKno save = resourcesRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<ResourcesKno> list(){
        List<ResourcesKno> aboutlist = resourcesRepo.findAll();
        return aboutlist;
    }


    private ResourcesKno updateDetails(long id, ResourcesKno abouts) {

        Optional<ResourcesKno> optionalUser = resourcesRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        ResourcesKno about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        ResourcesKno modifyAbout = resourcesRepo.save(about);


        return modifyAbout;
    }

    public ResourcesKno update(long id, ResourcesKno aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        resourcesRepo.deleteById(id);
        Optional<ResourcesKno> user = resourcesRepo.findById(id);
        return !user.isPresent();
    }

    public ResourcesKno findByid(Long id) {

        Optional<ResourcesKno> optionalUser = resourcesRepo.findById(id);
        return optionalUser.get();
    }
}

