package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import com.msp.webportal.businesslogic.model.faq.FaqQuestionAudit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FaqQuestionToFaqQuestionAudit implements Converter<FaqQuestion, FaqQuestionAudit> {
    @Override
    public FaqQuestionAudit convert(FaqQuestion faqQuestion) {
        FaqQuestionAudit faqQuestionAudit = new FaqQuestionAudit();
        faqQuestionAudit.setId(faqQuestion.getId());
        faqQuestionAudit.setQuestion(faqQuestion.getQuestion());
        faqQuestionAudit.setStatus(faqQuestion.isStatus());
        faqQuestionAudit.setCreatedDate(faqQuestion.getCreatedDate());
        faqQuestionAudit.setUpdatedDate(faqQuestion.getUpdatedDate());
        return faqQuestionAudit;
    }
}
