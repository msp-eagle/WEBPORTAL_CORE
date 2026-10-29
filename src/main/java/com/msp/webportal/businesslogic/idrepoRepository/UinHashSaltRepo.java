package com.msp.webportal.businesslogic.idrepoRepository;

import com.msp.webportal.businesslogic.idrepoEntity.UinHashSalt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UinHashSaltRepo extends JpaRepository<UinHashSalt, Integer > {

    @Query("select salt from UinHashSalt where id =:id")
    String retrieveSaltById(@Param("id") int var1);
}


