package com.msp.webportal.businesslogic.repository.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.AboutCareers;
import com.msp.webportal.businesslogic.model.about.phisyscouncil.Compotition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompositionRepository extends JpaRepository<Compotition,Long> {
}
