package com.msp.webportal.businesslogic.converter.ephil;

import com.msp.webportal.businesslogic.dto.ephilid.EphilfaqDto;
import com.msp.webportal.businesslogic.dto.faq.FaqAnswerDto;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EphilFaqToEphilFaqDto implements Converter<EphilFaq, EphilfaqDto> {

    @Override
    public EphilfaqDto convert(EphilFaq faqAnswer) {
        EphilfaqDto faqAnswerDto = new EphilfaqDto();
        faqAnswerDto.setId(faqAnswer.getId());
        faqAnswerDto.setQuestion(faqAnswer.getQuestion());
        faqAnswerDto.setAnswer(faqAnswer.getAnswer());
        faqAnswerDto.setStatus(faqAnswer.isStatus());
        faqAnswerDto.setCreatedDate(faqAnswer.getCreatedDate());

        faqAnswerDto.setFaqid(faqAnswer.getFaqid());
        faqAnswerDto.setFaqCollapse(faqAnswer.getFaqCollapse());

        return faqAnswerDto;
    }
}
