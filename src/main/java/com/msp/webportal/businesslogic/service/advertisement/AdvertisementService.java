package com.msp.webportal.businesslogic.service.advertisement;


import com.msp.webportal.businesslogic.dto.advertisement.Advertisementdto;
import com.msp.webportal.businesslogic.model.advertisement.Advertisement;
import com.msp.webportal.businesslogic.repository.advertisement.AdvertisementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepo advertisementRepo;

    public Advertisement saveAdvertisement(Advertisementdto advertisementdto) throws IOException {
        Advertisement advertisement=new Advertisement();
        advertisement.setAdvtitle(advertisementdto.getAdvtitle());
        advertisement.setAdvdesc(advertisementdto.getAdvdesc());
        advertisement.setImage(advertisementdto.getImage().getBytes());


        DateFormat inputFormat = new SimpleDateFormat(
                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(advertisementdto.getAdvdate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        advertisement.setAdvdate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        advertisement.setAdvtime(LocalTime.parse(advertisementdto.getAdvtime()));
        return advertisementRepo.save(advertisement);
    }


    public List<Advertisement> getadvList() {
        return advertisementRepo.findAll();
    }


    public Optional<Advertisement> findByAdvId(int id) {
        Optional<Advertisement> advertisement = advertisementRepo.findById(id);
        return advertisement;
    }

    public Advertisement advrtisementUpdate(Advertisementdto advertisementdto) throws IOException {

        Advertisement advertisement=new Advertisement();
        advertisement.setId(advertisementdto.getId());
        advertisement.setAdvtitle(advertisementdto.getAdvtitle());

        advertisement.setImage(advertisementdto.getImage().getBytes());
        advertisement.setAdvdesc(advertisementdto.getAdvdesc());
        DateFormat inputFormat = new SimpleDateFormat(
                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(advertisementdto.getAdvdate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        advertisement.setAdvdate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        advertisement.setAdvtime(LocalTime.parse(advertisementdto.getAdvtime()));

        return  advertisementRepo.save(advertisement);
    }
    public Optional<Advertisement> findById(int id) {
        Optional<Advertisement> advertisement = advertisementRepo.findById(id);
        return advertisement;
    }


    public void delete(Advertisement advertisement) {

            advertisementRepo.delete(advertisement);

    }
}
