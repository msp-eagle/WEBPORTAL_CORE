package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.dto.faq.FaqQuestionDto;
import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FaqQuestionDtoToQuestion implements Converter<FaqQuestionDto, FaqQuestion> {
    @Override
    public FaqQuestion convert(FaqQuestionDto faqQuestionDto) {
        FaqQuestion faqQuestion = new FaqQuestion();
        faqQuestion.setId(faqQuestionDto.getId());
        faqQuestion.setQuestion(faqQuestionDto.getQuestion());
        faqQuestion.setStatus(faqQuestionDto.isStatus());
        faqQuestion.setCreatedDate(faqQuestionDto.getCreatedDate());
        return faqQuestion;
    }
}
