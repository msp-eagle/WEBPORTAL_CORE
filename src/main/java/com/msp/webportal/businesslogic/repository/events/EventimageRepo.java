package com.msp.webportal.businesslogic.repository.events;

import com.msp.webportal.businesslogic.model.event.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventimageRepo extends JpaRepository<EventImage,Integer> {
}
