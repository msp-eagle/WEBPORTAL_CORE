package com.msp.webportal.businesslogic.repository.login;

import com.msp.webportal.businesslogic.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Repository
public interface LoginRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u where u.confirmPassword=:pwd and u.pcn=:pcn")
    User getAllPersons(String pcn, String pwd);
    @Query(value = "SELECT u.loggeddate FROM User u where u.pcn=:pcn")
    LocalDateTime getLoggedDateByPcn(String pcn);
    @Modifying
    @Query(value = "update User u set u.confirmPassword=:newpwd where u.pcn=:pcn")
    int changepwdmail(String newpwd, String pcn);
    @Modifying
    @Query("UPDATE User u SET u.loggeddate = :loginTime WHERE u.pcn = :pcn")
    void updateLastLoginTime(String pcn,LocalDateTime loginTime);

    boolean existsByPcn(String pcn);

    User findByPcn(String pcn);

}
