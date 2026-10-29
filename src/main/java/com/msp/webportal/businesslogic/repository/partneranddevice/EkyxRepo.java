package com.msp.webportal.businesslogic.repository.partneranddevice;


import com.msp.webportal.businesslogic.model.partnerandevice.Ekyc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EkyxRepo extends JpaRepository<Ekyc,Long> {
}
