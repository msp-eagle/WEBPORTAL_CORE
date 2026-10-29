package com.msp.webportal.businesslogic.repository.philysyscheck;

import com.msp.webportal.businesslogic.model.philysyscheck.Philyps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PhilypsRepo extends JpaRepository<Philyps,Long>{

}
