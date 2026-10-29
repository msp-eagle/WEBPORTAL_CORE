package com.msp.webportal.businesslogic.model.crms;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="feedback")
@Builder
public class Feedback {


    @Column(name ="name")
    private String name;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="comment")
    private String comment;

    @Column(name="pcn")
private String pcn;

    @Column(name="ticket")
    private String ticket;


}

