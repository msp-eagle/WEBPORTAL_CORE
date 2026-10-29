package com.msp.webportal.businesslogic.repository.adminmanagement;

import com.msp.webportal.businesslogic.model.adminmagement.AdminManager;
import com.msp.webportal.businesslogic.model.adminmagement.Adminlogin;
import com.msp.webportal.businesslogic.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepo extends JpaRepository<Adminlogin,Long> {

    @Query(value = "SELECT u FROM Adminlogin u where u.email=:email and u.password=:password ")
    Adminlogin getallperson(String email, String password);
}
