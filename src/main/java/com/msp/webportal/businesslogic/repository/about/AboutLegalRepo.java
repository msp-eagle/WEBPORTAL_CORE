package com.msp.webportal.businesslogic.repository.about;

import com.msp.webportal.businesslogic.model.about.AboutLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutLegalRepo extends JpaRepository<AboutLegal,Long> {
}
