package com.msp.webportal.businesslogic.converter;


import com.msp.webportal.businesslogic.dto.contact.ContactDto;
import com.msp.webportal.businesslogic.model.contact.Contact;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactDto implements Converter<Contact, ContactDto> {

    @Override
    public ContactDto convert(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setComments(contact.getComments());
        contactDto.setEmail(contact.getEmail());
        return contactDto;
    }

}
