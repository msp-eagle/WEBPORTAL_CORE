package com.msp.webportal.businesslogic.repository.advertisement;

import com.msp.webportal.businesslogic.model.advertisement.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepo extends JpaRepository<Advertisement,Integer> {
}
