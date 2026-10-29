package com.msp.webportal.businesslogic.service.ephilid;

import com.msp.webportal.businesslogic.dto.ephilid.Ephilgallerydto;
import com.msp.webportal.businesslogic.dto.event.EventImagedto;
import com.msp.webportal.businesslogic.model.ephilid.EphilGallery;
import com.msp.webportal.businesslogic.model.ephilid.EphilId;
import com.msp.webportal.businesslogic.model.event.EventImage;
import com.msp.webportal.businesslogic.model.philysyscheck.Philyps;
import com.msp.webportal.businesslogic.repository.ephilid.EphilGalleryRepo;
import com.msp.webportal.businesslogic.repository.ephilid.EphilidRepo;
import com.msp.webportal.businesslogic.repository.philysyscheck.PhilypsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
@Service
public class EphilidService {
    @Autowired
    private EphilidRepo  ephilidRepo;
    @Autowired
    private EphilGalleryRepo ephilGalleryRepo;


    public EphilId save(EphilId ephilId){
        EphilId save = ephilidRepo.save(ephilId);
        return save;
    }

    public List<EphilId> list(){
        List<EphilId> aboutlist = ephilidRepo.findAll();
        return aboutlist;
    }

    private EphilId updateDetails(long id, EphilId ephilId) {

        Optional<EphilId> optionalUser = ephilidRepo.findById(id);
        if(!optionalUser.isPresent()){
        }
        EphilId ephilIds = optionalUser.get();
        ephilIds.setId(id);
        ephilIds.setContent(ephilId.getContent());
        ephilIds.setContentTitle(ephilId.getContentTitle());
        EphilId modifyAbout = ephilidRepo.save(ephilIds);


        return modifyAbout;
    }


    public EphilId update(long id,EphilId aboutDataPrivacy) {
        return updateDetails(id, aboutDataPrivacy);
    }


    public boolean deleteById(Long id) {

        ephilidRepo.deleteById(id);
        Optional<EphilId> user = ephilidRepo.findById(id);
        return !user.isPresent();
    }

    public EphilId findByid(Long id) {

        Optional<EphilId> optionalUser = ephilidRepo.findById(id);
        return optionalUser.get();
    }
    public EphilGallery saveEvents(Ephilgallerydto eventdto) throws IOException, ParseException {
        EphilGallery events=new EphilGallery();
        events.setImage(eventdto.getImage().getBytes());
        return  ephilGalleryRepo.save(events);
    }
    public List<EphilGallery> getList() {
        return ephilGalleryRepo.findAll();
    }

    public EphilGallery eventUpdate(Ephilgallerydto eventdto) throws IOException {
        EphilGallery events=new EphilGallery();
        events.setId(eventdto.getId());
        events.setImage(eventdto.getImage().getBytes());
        return  ephilGalleryRepo.save(events);
    }

    public void delete(EphilGallery events) {
        ephilGalleryRepo.delete(events);
    }


    public Optional<EphilGallery> findById(int id) {
        Optional<EphilGallery> events = ephilGalleryRepo.findById(id);
        return events;
    }
}
