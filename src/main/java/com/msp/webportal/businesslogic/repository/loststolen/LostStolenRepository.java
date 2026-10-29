package com.msp.webportal.businesslogic.repository.loststolen;

import com.msp.webportal.businesslogic.model.loststolen.Loststolen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostStolenRepository extends JpaRepository<Loststolen,Long> {
}
