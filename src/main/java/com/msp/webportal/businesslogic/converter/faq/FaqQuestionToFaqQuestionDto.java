package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.dto.faq.FaqQuestionDto;
import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FaqQuestionToFaqQuestionDto implements Converter<FaqQuestion, FaqQuestionDto> {
    @Override
    public FaqQuestionDto convert(FaqQuestion faqQuestion) {
        FaqQuestionDto faqQuestionDto = new FaqQuestionDto();
        faqQuestionDto.setId(faqQuestion.getId());
        faqQuestionDto.setQuestion(faqQuestion.getQuestion());
        faqQuestionDto.setStatus(faqQuestion.isStatus());
        faqQuestionDto.setCreatedDate(faqQuestion.getCreatedDate());

        return faqQuestionDto;
    }
}
