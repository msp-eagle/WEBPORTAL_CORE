package com.msp.webportal.businesslogic.repository.home;


import com.msp.webportal.businesslogic.model.home.Homeimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Homeimagerepo extends JpaRepository<Homeimage, Integer> {


    @Query(value = "select s from home_image where s.sno =:id",nativeQuery = true)
    Optional<Homeimage> findByID(int id);
}