package com.msp.webportal.businesslogic.service.testimonial;

import com.msp.webportal.businesslogic.dto.event.Eventdto;
import com.msp.webportal.businesslogic.dto.testimonals.TestDto;
import com.msp.webportal.businesslogic.model.event.Events;
import com.msp.webportal.businesslogic.model.testimonals.Testimonal;
import com.msp.webportal.businesslogic.repository.events.EventRepo;
import com.msp.webportal.businesslogic.repository.testimomial.TestimonialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Service
public class TestimonialService {
    @Autowired
    private TestimonialRepo testimonialRepo;


    public Testimonal saveEvents(TestDto testDto) throws IOException, ParseException {
        Testimonal events=new Testimonal();
        events.setTitle(testDto.getTitle());
        events.setDescription(testDto.getDescription());
        events.setAddress(testDto.getAddress());
        events.setImage(testDto.getImage().getBytes());
        return  testimonialRepo.save(events);
    }



    public List<Testimonal> getList() {
        return testimonialRepo.findAll();
    }


    public Testimonal eventUpdate(TestDto testDto) throws IOException {
        Testimonal events=new Testimonal();
        events.setId(testDto.getId());
        events.setTitle(testDto.getTitle());

        events.setImage(testDto.getImage().getBytes());
        events.setDescription(testDto.getDescription());
        events.setAddress(testDto.getAddress());
        return  testimonialRepo.save(events);
    }

    public void delete(Testimonal events) {
        testimonialRepo.delete(events);
    }


    public Optional<Testimonal> findById(Long id) {
        Optional<Testimonal> events = testimonialRepo.findById(id);
        return events;
    }


}
