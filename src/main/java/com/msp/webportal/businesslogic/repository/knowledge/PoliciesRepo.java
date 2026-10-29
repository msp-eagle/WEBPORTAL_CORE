package com.msp.webportal.businesslogic.repository.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.PoliciesKno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PoliciesRepo extends JpaRepository<PoliciesKno,Long> {
}
