package com.msp.webportal.businesslogic.repository.testimomial;


import com.msp.webportal.businesslogic.model.testimonals.Testimonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepo extends JpaRepository<Testimonal, Long> {

//        @Query(value = "select s from Testimonal s where s.sno =:id",nativeQuery = true)
//        Optional<Testimonal> findByID(int id);
}
