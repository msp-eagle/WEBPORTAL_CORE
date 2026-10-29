package com.msp.webportal.businesslogic.converter;



import com.msp.webportal.businesslogic.dto.contact.ContactDto;
import com.msp.webportal.businesslogic.model.contact.Contact;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoToContact implements Converter<ContactDto, Contact> {

    @Override
    public Contact convert(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setName(contactDto.getName());
        contact.setComments(contactDto.getComments());
        contact.setEmail(contactDto.getEmail());
        System.out.println("this is a "+contact.getComments());
        return contact;
    }
}
