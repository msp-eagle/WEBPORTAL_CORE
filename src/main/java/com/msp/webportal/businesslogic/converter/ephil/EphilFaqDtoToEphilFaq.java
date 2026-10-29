package com.msp.webportal.businesslogic.converter.ephil;

//import com.service.philyps.dto.ephilid.EphilfaqDto;
//import com.service.philyps.dto.faq.FaqAnswerDto;
//import com.service.philyps.model.ephilid.EphilFaq;
//import com.service.philyps.model.faq.FaqAnswer;
import com.msp.webportal.businesslogic.dto.ephilid.EphilfaqDto;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class EphilFaqDtoToEphilFaq implements Converter<EphilfaqDto, EphilFaq>{

        @Override
        public EphilFaq convert(EphilfaqDto faqAnswerDto) {
            EphilFaq faqAnswer = new EphilFaq();
            faqAnswer.setId(faqAnswerDto.getId());
            faqAnswer.setQuestion(faqAnswerDto.getQuestion());
            faqAnswer.setAnswer(faqAnswerDto.getAnswer());
            faqAnswer.setStatus(faqAnswerDto.isStatus());
            faqAnswer.setCreatedDate(faqAnswerDto.getCreatedDate());

            faqAnswer.setFaqid(faqAnswerDto.getFaqid());
            faqAnswer.setFaqCollapse(faqAnswerDto.getFaqCollapse());
            return faqAnswer;
        }

}
