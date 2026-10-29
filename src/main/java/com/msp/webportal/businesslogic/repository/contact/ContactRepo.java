package com.msp.webportal.businesslogic.repository.contact;

import com.msp.webportal.businesslogic.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
}
