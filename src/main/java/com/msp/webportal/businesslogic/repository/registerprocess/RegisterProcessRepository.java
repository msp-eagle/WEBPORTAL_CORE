package com.msp.webportal.businesslogic.repository.registerprocess;

import com.msp.webportal.businesslogic.model.registerprocess.Registerprocess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterProcessRepository extends JpaRepository<Registerprocess,Long> {
}
