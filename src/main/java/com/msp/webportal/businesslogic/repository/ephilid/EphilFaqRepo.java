package com.msp.webportal.businesslogic.repository.ephilid;

import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EphilFaqRepo extends JpaRepository<EphilFaq,Long>{

        @Query("select count(*) as counter from EphilFaq")
        Long findCount();

        @Query("select count(*) as counter from EphilFaq WHERE createdDate >= date_trunc('week', CURRENT_DATE)")
        Long findbyWeek();

        @Query("select count(*) as counter from EphilFaq WHERE createdDate >= date_trunc('month', CURRENT_DATE) ")
        Long findbyMonth();
}
