package com.msp.webportal.businesslogic.repository.faq;


import com.msp.webportal.businesslogic.model.faq.FaqQuestionAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqQuestionAuditRepository extends JpaRepository<FaqQuestionAudit,Long> {
}
