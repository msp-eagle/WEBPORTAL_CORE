package com.msp.webportal.businesslogic.service.faq;


import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.faq.FaqAnswerDto;
import com.msp.webportal.businesslogic.dto.faq.FaqCount;
import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import com.msp.webportal.businesslogic.response.Apiresponses;

public interface FaqAnswerService {

    public FaqAnswer save(FaqAnswerDto faqAnswerDto);

    Apiresponses pageList(Pageable pageable);

    FaqAnswer findByid(Long id);

    FaqAnswer update(long id, FaqAnswerDto faqAnswerDto);

    boolean deleteById(Long id);

    FaqAnswerDto changeAnswerStatus(boolean status, long id);

    FaqCount mangaloreAnswerCount();
}
