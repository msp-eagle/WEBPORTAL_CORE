package com.msp.webportal.businesslogic.repository.otp_log;

import com.msp.webportal.businesslogic.model.otp_log.OTPLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OtpLogRepository extends JpaRepository<OTPLog, Long > {
    Optional<OTPLog> findByPcn(String pcn);
}
