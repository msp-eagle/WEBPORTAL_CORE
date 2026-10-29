package com.msp.webportal.businesslogic.repository.usecase;

import com.msp.webportal.businesslogic.model.usecase.Usecase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsecaseRepo extends JpaRepository<Usecase,Long> {
}
