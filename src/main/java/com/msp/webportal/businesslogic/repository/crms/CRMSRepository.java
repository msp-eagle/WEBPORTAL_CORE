package com.msp.webportal.businesslogic.repository.crms;

import com.msp.webportal.businesslogic.model.crms.CRMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRMSRepository extends JpaRepository<CRMSEntity,String> {
}
