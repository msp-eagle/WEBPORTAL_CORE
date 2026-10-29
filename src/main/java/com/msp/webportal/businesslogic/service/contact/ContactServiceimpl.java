package com.msp.webportal.businesslogic.service.contact;

import com.msp.webportal.businesslogic.converter.ContactDtoToContact;
import com.msp.webportal.businesslogic.converter.ContactToContactDto;
import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.contact.ContactDto;
import com.msp.webportal.businesslogic.model.contact.Contact;
import com.msp.webportal.businesslogic.repository.contact.ContactRepo;
import com.msp.webportal.businesslogic.response.Apiresponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceimpl implements ContactService {


    @Autowired
    private ContactDtoToContact contactDtoToContact;
    @Autowired
    private ContactToContactDto contactToContactDto;
    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(ContactDto contactDto) {

        Contact contact = contactDtoToContact.convert(contactDto);

        contact.setCreatedAt(LocalDateTime.now());
        Contact contactSave = contactRepo.save(contact);

        return contactSave;
    }

    @Override
    public Apiresponses findAll(Pageable pageable) {
        Apiresponses apiResponce = new Apiresponses();
        Long totalCount = contactRepo.count();
        List<ContactDto> list = new ArrayList<>();
        contactRepo.findAll(PageRequest.of(pageable.getStart(), pageable.getSize(), Sort.by("createdAt").descending())).stream().forEach(
                data -> list.add(contactToContactDto.convert(data)));
        apiResponce.setStatus(true);
        apiResponce.setTotalRecords(totalCount);
        apiResponce.setData(list);
        return apiResponce;
    }

}
