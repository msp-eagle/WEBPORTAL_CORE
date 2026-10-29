package com.msp.webportal.businesslogic.repository.events;

import com.msp.webportal.businesslogic.model.event.Evententity;
import com.msp.webportal.businesslogic.model.event.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventscontentRep extends JpaRepository<Evententity, Long> {
}
