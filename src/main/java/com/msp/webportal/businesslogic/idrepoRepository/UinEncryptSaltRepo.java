package com.msp.webportal.businesslogic.idrepoRepository;

import com.msp.webportal.businesslogic.idrepoEntity.UinEncryptSalt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UinEncryptSaltRepo extends JpaRepository<UinEncryptSalt, Integer> {

    @Query(value = "select salt from UinEncryptSalt where id = :id")
    String retrieveSaltById(@Param("id") int var1);
}
