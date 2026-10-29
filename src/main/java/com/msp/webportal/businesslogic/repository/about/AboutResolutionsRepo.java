package com.msp.webportal.businesslogic.repository.about;


import com.msp.webportal.businesslogic.model.about.AboutResolutions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutResolutionsRepo  extends JpaRepository<AboutResolutions,Long> {
}
