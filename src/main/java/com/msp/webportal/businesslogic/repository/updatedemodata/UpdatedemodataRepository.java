package com.msp.webportal.businesslogic.repository.updatedemodata;

import com.msp.webportal.businesslogic.model.updatedemodata.Updatedemodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedemodataRepository extends JpaRepository<Updatedemodata,Long> {
}
