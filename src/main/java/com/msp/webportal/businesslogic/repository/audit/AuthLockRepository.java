package com.msp.webportal.businesslogic.repository.audit;

import com.msp.webportal.businesslogic.model.audit.AuthLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthLockRepository extends JpaRepository<AuthLock,Long> {
}
