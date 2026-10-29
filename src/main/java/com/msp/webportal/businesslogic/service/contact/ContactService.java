package com.msp.webportal.businesslogic.service.contact;

import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.contact.ContactDto;
import com.msp.webportal.businesslogic.model.contact.Contact;
import com.msp.webportal.businesslogic.response.Apiresponses;

public interface ContactService {

    Contact save(ContactDto contactDto);

    Apiresponses findAll(Pageable pageable);
}
