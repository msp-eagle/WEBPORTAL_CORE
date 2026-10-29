package com.msp.webportal.businesslogic.converter.faq;


import com.msp.webportal.businesslogic.dto.faq.FaqAnswerDto;
import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FaqAnswerDtoToFaqAnswer implements Converter<FaqAnswerDto, FaqAnswer> {
    @Override
    public FaqAnswer convert(FaqAnswerDto faqAnswerDto) {
        FaqAnswer faqAnswer = new FaqAnswer();
        faqAnswer.setId(faqAnswerDto.getId());
        faqAnswer.setTopics(faqAnswerDto.getTopics());
        faqAnswer.setQuestion(faqAnswerDto.getQuestion());
        faqAnswer.setAnswer(faqAnswerDto.getAnswer());
        faqAnswer.setStatus(faqAnswerDto.isStatus());
        faqAnswer.setCreatedDate(faqAnswerDto.getCreatedDate());

        faqAnswer.setFaqid(faqAnswerDto.getFaqid());
        faqAnswer.setFaqCollapse(faqAnswerDto.getFaqCollapse());
        return faqAnswer;
    }
}
