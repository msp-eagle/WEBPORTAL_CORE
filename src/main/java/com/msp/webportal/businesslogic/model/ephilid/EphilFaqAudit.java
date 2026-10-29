package com.msp.webportal.businesslogic.model.ephilid;

import com.msp.webportal.businesslogic.commonUtil.UniqueIdGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ephilfaqAudit")
public class EphilFaqAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String faqid = UniqueIdGenerator.getId();
    private String faqCollapse;
    @Column(name = "answer", length = 1000)
    private String answer;
    private boolean status;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDate updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getFaqid() {
        return faqid;
    }

    public void setFaqid(String faqid) {
        this.faqid = faqid;
    }

    public String getFaqCollapse() {
        return faqCollapse;
    }

    public void setFaqCollapse(String faqCollapse) {
        this.faqCollapse = faqCollapse;
    }
}
