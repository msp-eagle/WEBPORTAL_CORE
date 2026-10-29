package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import com.msp.webportal.businesslogic.model.faq.FaqAnswerAudit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component

public class FaqAnswerToFaqAnswerAudit implements Converter<FaqAnswer, FaqAnswerAudit> {
    @Override
    public FaqAnswerAudit convert(FaqAnswer faqAnswer) {
        FaqAnswerAudit faqAnswerAudit = new FaqAnswerAudit();
        faqAnswerAudit.setId(faqAnswer.getId());
        faqAnswerAudit.setTopics(faqAnswer.getTopics());
        faqAnswerAudit.setQuestion(faqAnswer.getQuestion());
        faqAnswerAudit.setAnswer(faqAnswer.getAnswer());
        faqAnswerAudit.setStatus(faqAnswer.isStatus());
        faqAnswerAudit.setCreatedDate(faqAnswer.getCreatedDate());
        faqAnswerAudit.setUpdatedDate(faqAnswer.getUpdatedDate());
        faqAnswerAudit.setFaqid(faqAnswer.getFaqid());
        faqAnswerAudit.setFaqCollapse(faqAnswer.getFaqCollapse());

        return faqAnswerAudit;
    }
}
