package com.msp.webportal.businesslogic.repository.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.phisyscouncil.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution,Long> {
}
