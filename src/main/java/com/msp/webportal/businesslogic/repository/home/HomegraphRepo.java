package com.msp.webportal.businesslogic.repository.home;

import com.msp.webportal.businesslogic.model.home.Homegraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomegraphRepo extends JpaRepository<Homegraph,Integer> {
}
