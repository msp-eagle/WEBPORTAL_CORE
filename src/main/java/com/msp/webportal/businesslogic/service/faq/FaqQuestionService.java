package com.msp.webportal.businesslogic.service.faq;


import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.faq.FaqQuestionDto;
import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import com.msp.webportal.businesslogic.response.Apiresponses;

public interface FaqQuestionService {
    public FaqQuestion save(FaqQuestionDto faqQuestionDto);

    Apiresponses pageList(Pageable pageable);

    FaqQuestion findByid(Long id);

    FaqQuestion update(long id, FaqQuestionDto faqQuestionDto);

    boolean deleteById(Long id);
}
