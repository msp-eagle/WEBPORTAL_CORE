package com.msp.webportal.businesslogic.repository.ephilid;

import com.msp.webportal.businesslogic.model.ephilid.EphilFaqAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EphilfaqAuditRepo extends JpaRepository<EphilFaqAudit,Long> {
}
