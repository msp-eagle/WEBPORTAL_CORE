package com.msp.webportal.businesslogic.repository.faq;


import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqAnswerRepo extends JpaRepository<FaqAnswer,Long> {

    @Query("select count(*) as counter from FaqAnswer")
    Long findCount();

    @Query("select count(*) as counter from FaqAnswer WHERE createdDate >= date_trunc('week', CURRENT_DATE)")
    Long findbyWeek();

    @Query("select count(*) as counter from FaqAnswer WHERE createdDate >= date_trunc('month', CURRENT_DATE) ")
    Long findbyMonth();
}
