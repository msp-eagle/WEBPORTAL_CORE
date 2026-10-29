package com.msp.webportal.businesslogic.repository.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.ProceduresKno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProceduresRepo extends JpaRepository<ProceduresKno,Long> {
}
