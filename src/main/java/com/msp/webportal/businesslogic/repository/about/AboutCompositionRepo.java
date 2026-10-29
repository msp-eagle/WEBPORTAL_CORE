package com.msp.webportal.businesslogic.repository.about;

import com.msp.webportal.businesslogic.model.about.AboutComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutCompositionRepo extends JpaRepository<AboutComposition,Long> {
}
