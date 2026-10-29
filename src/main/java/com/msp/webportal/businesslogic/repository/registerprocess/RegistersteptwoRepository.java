package com.msp.webportal.businesslogic.repository.registerprocess;

import com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistersteptwoRepository extends JpaRepository<Registersteptwo,Long> {
}
