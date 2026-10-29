package com.msp.webportal.businesslogic.repository.about;


import com.msp.webportal.businesslogic.model.about.AboutNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutNumbersRepo extends JpaRepository<AboutNumbers,Long> {
}
