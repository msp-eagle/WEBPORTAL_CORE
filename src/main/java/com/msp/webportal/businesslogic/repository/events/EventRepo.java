package com.msp.webportal.businesslogic.repository.events;


import com.msp.webportal.businesslogic.model.event.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Events, Integer> {


    @Query(value = "select  * from table_event s where s.event_todate between  :date and :with ", nativeQuery = true)
    List<Events> findAllByEventDateBetween(LocalDate date, LocalDate with);
    @Query(value = "select s from table_event s where s.sno =:id",nativeQuery = true)
    Optional<Events> findByID(int id);



}
