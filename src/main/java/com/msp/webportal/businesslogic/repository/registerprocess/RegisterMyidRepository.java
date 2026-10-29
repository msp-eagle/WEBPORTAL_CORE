package com.msp.webportal.businesslogic.repository.registerprocess;

import com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMyidRepository extends JpaRepository<RegisterMyid,Long> {
}
