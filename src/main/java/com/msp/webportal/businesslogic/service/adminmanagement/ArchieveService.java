package com.msp.webportal.businesslogic.service.adminmanagement;

import com.msp.webportal.businesslogic.dto.advertisement.ArchieveDto;
import com.msp.webportal.businesslogic.dto.testimonals.TestDto;
import com.msp.webportal.businesslogic.model.adminmagement.Archieve;
import com.msp.webportal.businesslogic.model.testimonals.Testimonal;
import com.msp.webportal.businesslogic.repository.adminmanagement.ArchieveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class ArchieveService {

    @Autowired
    private ArchieveRepo archieveRepo;

    public Archieve saveEvents(ArchieveDto testDto) throws IOException, ParseException {
        Archieve events=new Archieve();
        events.setTitle(testDto.getTitle());
        events.setDescription(testDto.getDescription());
        events.setDate(testDto.getDate());
        events.setImage(testDto.getImage().getBytes());
        events.setContent(testDto.getContent());
        events.setUrl("/"+testDto.getDate());
        return  archieveRepo.save(events);
    }
}
