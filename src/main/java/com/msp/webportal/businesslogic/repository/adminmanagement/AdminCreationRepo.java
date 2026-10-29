package com.msp.webportal.businesslogic.repository.adminmanagement;


import com.msp.webportal.businesslogic.model.adminmagement.AdminManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCreationRepo extends JpaRepository<AdminManager,Long> {
    @Query(value = "SELECT u FROM AdminManager u where u.email=:email and u.password=:password ")
    AdminManager getallperson(String email, String password);

   // AdminManager getallperson(String email);

    AdminManager findByEmail(String email);

}
