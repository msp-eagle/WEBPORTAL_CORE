package com.msp.webportal.businesslogic.repository.about;

import com.msp.webportal.businesslogic.model.about.AboutFunctions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutFunctionsRepo extends JpaRepository<AboutFunctions,Long> {
}
