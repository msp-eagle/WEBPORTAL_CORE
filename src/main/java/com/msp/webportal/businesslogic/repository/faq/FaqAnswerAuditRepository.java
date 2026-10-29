package com.msp.webportal.businesslogic.repository.faq;


import com.msp.webportal.businesslogic.model.faq.FaqAnswerAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqAnswerAuditRepository extends JpaRepository<FaqAnswerAudit,Long> {
}
