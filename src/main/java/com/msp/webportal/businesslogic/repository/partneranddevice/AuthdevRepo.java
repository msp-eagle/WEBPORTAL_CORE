package com.msp.webportal.businesslogic.repository.partneranddevice;

import com.msp.webportal.businesslogic.model.partnerandevice.AuthDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthdevRepo extends JpaRepository<AuthDevice,Long> {
}
