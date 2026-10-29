package com.msp.webportal.businesslogic.repository.ephilid;

import com.msp.webportal.businesslogic.model.ephilid.EphilId;
import com.msp.webportal.businesslogic.model.philysyscheck.Philyps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EphilidRepo extends JpaRepository<EphilId,Long> {
}
