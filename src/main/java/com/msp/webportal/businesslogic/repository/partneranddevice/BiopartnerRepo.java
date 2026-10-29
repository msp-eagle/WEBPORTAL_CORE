package com.msp.webportal.businesslogic.repository.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.Biopartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiopartnerRepo extends JpaRepository<Biopartner,Long> {
}
