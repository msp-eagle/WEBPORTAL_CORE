package com.msp.webportal.businesslogic.repository.faq;


import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqQuestionRepo extends JpaRepository<FaqQuestion,Long> {
}
