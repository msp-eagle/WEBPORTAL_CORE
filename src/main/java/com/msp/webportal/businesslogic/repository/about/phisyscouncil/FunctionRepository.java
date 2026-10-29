package com.msp.webportal.businesslogic.repository.about.phisyscouncil;

import com.msp.webportal.businesslogic.model.about.phisyscouncil.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FunctionRepository extends JpaRepository<Function,Long> {
}
