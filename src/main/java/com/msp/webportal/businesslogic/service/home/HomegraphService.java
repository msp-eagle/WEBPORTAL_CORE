package com.msp.webportal.businesslogic.service.home;

import com.msp.webportal.businesslogic.dto.home.homedto;
import com.msp.webportal.businesslogic.dto.home.homegraphdto;
import com.msp.webportal.businesslogic.model.home.Homegraph;
import com.msp.webportal.businesslogic.model.home.Homeimage;
import com.msp.webportal.businesslogic.repository.home.HomegraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class HomegraphService {

@Autowired
private HomegraphRepo homegraphRepo;

    public Homegraph saveEvents(homegraphdto eventdto) throws IOException, ParseException {
        Homegraph events=new Homegraph();
        events.setImage(eventdto.getImage().getBytes());
        return  homegraphRepo.save(events);
    }
    public List<Homegraph> getList() {
        return homegraphRepo.findAll();
    }

    public Homegraph eventUpdate(homegraphdto eventdto) throws IOException {
        Homegraph events=new Homegraph();
        events.setId(eventdto.getId());
        events.setImage(eventdto.getImage().getBytes());
        return  homegraphRepo.save(events);
    }

    public void delete(Homegraph events) {
        homegraphRepo.delete(events);
    }


    public Optional<Homegraph> findById(int id) {
        Optional<Homegraph> events = homegraphRepo.findById(id);
        return events;
    }
}
