package com.msp.webportal.businesslogic.repository.home;

import com.msp.webportal.businesslogic.model.home.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home,Long> {

}
