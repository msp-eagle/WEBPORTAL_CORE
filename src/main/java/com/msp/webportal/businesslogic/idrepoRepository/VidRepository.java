package com.msp.webportal.businesslogic.idrepoRepository;

import com.msp.webportal.businesslogic.idrepoEntity.Vid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface VidRepository extends JpaRepository<Vid, String> {


    List<Vid> findByUinHash(String uinHash);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Vid i SET i.statusCode = 'DELETE' WHERE i.vid = :id")
    int changeAlyasStatusToDeleted(@Param("id") String id);

}