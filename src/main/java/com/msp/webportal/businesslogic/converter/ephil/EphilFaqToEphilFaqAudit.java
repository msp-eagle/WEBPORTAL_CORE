package com.msp.webportal.businesslogic.converter.ephil;


import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaqAudit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EphilFaqToEphilFaqAudit implements Converter<EphilFaq, EphilFaqAudit> {

        @Override
        public EphilFaqAudit convert(EphilFaq ephilFaq) {
            EphilFaqAudit faqAnswerAudit = new EphilFaqAudit();
        faqAnswerAudit.setId(ephilFaq.getId());
        faqAnswerAudit.setQuestion(ephilFaq.getQuestion());
        faqAnswerAudit.setAnswer(ephilFaq.getAnswer());
        faqAnswerAudit.setStatus(ephilFaq.isStatus());
        faqAnswerAudit.setCreatedDate(ephilFaq.getCreatedDate());
        faqAnswerAudit.setUpdatedDate(ephilFaq.getUpdatedDate());
        faqAnswerAudit.setFaqid(ephilFaq.getFaqid());
        faqAnswerAudit.setFaqCollapse(ephilFaq.getFaqCollapse());

        return faqAnswerAudit;
    }
    }
