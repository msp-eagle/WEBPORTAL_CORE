package com.msp.webportal.businesslogic.service.knowledge;

import  com.msp.webportal.businesslogic.model.knowledge.DocumentsKno;
import com.msp.webportal.businesslogic.repository.knowledge.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;


    public DocumentsKno save(DocumentsKno aboutDataPrivacy){
        DocumentsKno save = documentRepo.save(aboutDataPrivacy);
        return save;
    }

    public List<DocumentsKno> list(){
        List<DocumentsKno> aboutlist = documentRepo.findAll();
        return aboutlist;
    }


    private DocumentsKno updateDetails(long id, DocumentsKno abouts) {

        Optional<DocumentsKno> optionalUser = documentRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        DocumentsKno about = optionalUser.get();
        about.setId(id);
        about.setContent(abouts.getContent());
        about.setContentTitle(abouts.getContentTitle());
        DocumentsKno modifyAbout = documentRepo.save(about);


        return modifyAbout;
    }

    public DocumentsKno update(long id, DocumentsKno aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }

    public boolean deleteById(Long id) {

        documentRepo.deleteById(id);
        Optional<DocumentsKno> user = documentRepo.findById(id);
        return !user.isPresent();
    }

    public DocumentsKno findByid(Long id) {

        Optional<DocumentsKno> optionalUser = documentRepo.findById(id);
        return optionalUser.get();
    }
}


