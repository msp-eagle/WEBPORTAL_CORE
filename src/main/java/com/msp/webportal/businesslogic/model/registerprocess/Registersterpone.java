package com.msp.webportal.businesslogic.model.registerprocess;

import javax.persistence.*;

@Entity
@Table(name="registerStepOne")
public class Registersterpone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
