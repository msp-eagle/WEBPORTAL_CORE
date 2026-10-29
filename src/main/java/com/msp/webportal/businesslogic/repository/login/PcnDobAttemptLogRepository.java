package com.msp.webportal.businesslogic.repository.login;

import com.msp.webportal.businesslogic.model.login.PcnDobAttemptLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcnDobAttemptLogRepository extends JpaRepository<PcnDobAttemptLog, Long> {

    PcnDobAttemptLog findByPcn(String pcn);
}
