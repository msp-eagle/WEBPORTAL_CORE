package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.dto.faq.FaqAnswerDto;
import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FaqAnswerToFaqAnswerDto implements Converter<FaqAnswer, FaqAnswerDto> {
    @Override
    public FaqAnswerDto convert(FaqAnswer faqAnswer) {
        FaqAnswerDto faqAnswerDto = new FaqAnswerDto();
        faqAnswerDto.setId(faqAnswer.getId());
        faqAnswerDto.setTopics(faqAnswer.getTopics());
        faqAnswerDto.setQuestion(faqAnswer.getQuestion());
        faqAnswerDto.setAnswer(faqAnswer.getAnswer());
        faqAnswerDto.setStatus(faqAnswer.isStatus());
        faqAnswerDto.setCreatedDate(faqAnswer.getCreatedDate());

        faqAnswerDto.setFaqid(faqAnswer.getFaqid());
        faqAnswerDto.setFaqCollapse(faqAnswer.getFaqCollapse());

        return faqAnswerDto;
    }
}
