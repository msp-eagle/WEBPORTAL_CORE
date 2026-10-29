package com.msp.webportal.businesslogic.repository.adminmanagement;

import com.msp.webportal.businesslogic.model.adminmagement.Archieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchieveRepo extends JpaRepository<Archieve,Long> {
}
