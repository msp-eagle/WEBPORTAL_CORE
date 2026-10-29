package com.msp.webportal.businesslogic.service.home;

import com.msp.webportal.businesslogic.dto.home.homedto;
import com.msp.webportal.businesslogic.dto.locationModeldto;
import com.msp.webportal.businesslogic.model.home.Home;

import com.msp.webportal.businesslogic.model.home.Homeimage;
import com.msp.webportal.businesslogic.model.home.LocationModel;
import com.msp.webportal.businesslogic.repository.home.HomeRepository;
import com.msp.webportal.businesslogic.repository.home.Homeimagerepo;
import com.msp.webportal.businesslogic.repository.nearbyloc.RegisteredCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private RegisteredCenterRepository registeredCenterRepository;
    @Autowired
    private Homeimagerepo homeimagerepo;

    public Home save(Home home){
        Home save = homeRepository.save(home);
        return save;
    }
    public List<Home> list(){
        List<Home> homelist = homeRepository.findAll();
        return homelist;
    }

    public List<locationModeldto> nearByPfrc(String lat, String lon) {
        List<locationModeldto> pfrc = registeredCenterRepository.finddist(lat, lon);
        return pfrc;
    }

    public List<LocationModel> nearestPFRCWithProvince(String name){
            return registeredCenterRepository.findAllByAddr_line3(name);
//        return null;
    }

    public Homeimage saveEvents(homedto eventdto) throws IOException, ParseException {
        Homeimage events=new Homeimage();
        events.setImage(eventdto.getImage().getBytes());
        return  homeimagerepo.save(events);
    }

    public List<Homeimage> getList() {
        return homeimagerepo.findAll();
    }

    public Homeimage eventUpdate(homedto eventdto) throws IOException {
        Homeimage events=new Homeimage();
        events.setId(eventdto.getId());
        events.setImage(eventdto.getImage().getBytes());
        return  homeimagerepo.save(events);
    }

    public void delete(Homeimage events) {
        homeimagerepo.delete(events);
    }


    public Optional<Homeimage> findById(int id) {
        Optional<Homeimage> events = homeimagerepo.findById(id);
        return events;
    }

}
