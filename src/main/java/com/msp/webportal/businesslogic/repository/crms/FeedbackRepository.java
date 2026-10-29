package com.msp.webportal.businesslogic.repository.crms;

import com.msp.webportal.businesslogic.model.crms.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
