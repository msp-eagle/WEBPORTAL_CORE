package com.msp.webportal.businesslogic.repository.about;


import com.msp.webportal.businesslogic.model.about.AboutDataPrivacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutDataPrivacyRepo extends JpaRepository<AboutDataPrivacy,Long> {
}
