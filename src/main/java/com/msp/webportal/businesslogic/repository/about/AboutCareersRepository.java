package com.msp.webportal.businesslogic.repository.about;


import com.msp.webportal.businesslogic.model.about.AboutCareers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AboutCareersRepository  extends JpaRepository<AboutCareers,Long> {
}
