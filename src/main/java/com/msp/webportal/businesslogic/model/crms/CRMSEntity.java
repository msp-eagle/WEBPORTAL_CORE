package com.msp.webportal.businesslogic.model.crms;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "crms")
@Data
@Builder
public class CRMSEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "userid",length = 40)
    private String userId;
    @Column(name = "name",length = 50)
    private String name;
    @Column(name = "feedback")
    @Lob
    private String feedBack;
    @Column(name = "grievances")
    @Lob
    private String grievance;
    @Column(name = "status",length = 15)
    private String status;
    @Column(name="email")
    private String email;
    @Column(name="ticket")
    private String ticket;


}
