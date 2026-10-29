package com.msp.webportal.businesslogic.repository.knowledge;

import com.msp.webportal.businesslogic.model.knowledge.MediaKno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MediaRepo extends JpaRepository<MediaKno,Long> {
}
