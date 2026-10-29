package com.msp.webportal.businesslogic.repository.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.phisyscouncil.Interagency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InteragencyRepository extends JpaRepository<Interagency,Long> {
}
