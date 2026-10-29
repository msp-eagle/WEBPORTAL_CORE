package com.msp.webportal.businesslogic.repository.ephilid;

import com.msp.webportal.businesslogic.model.ephilid.EphilGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EphilGalleryRepo extends JpaRepository<EphilGallery, Integer> {
}
