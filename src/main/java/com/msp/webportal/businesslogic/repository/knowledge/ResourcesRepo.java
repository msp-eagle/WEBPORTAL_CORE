package com.msp.webportal.businesslogic.repository.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.ResourcesKno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepo extends JpaRepository<ResourcesKno,Long> {
}
