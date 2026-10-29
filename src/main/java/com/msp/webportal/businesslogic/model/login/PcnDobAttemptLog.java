package com.msp.webportal.businesslogic.model.login;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "pcn_dob_attempt_log")
@AllArgsConstructor
@NoArgsConstructor
public class PcnDobAttemptLog {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    private String pcn;
    private Integer failureCount;

    @Column(name = "failure_count_expired_at")
    private LocalDateTime failureCountExpiredAt;
}
