package com.msp.webportal.businesslogic.service.ephilid;


import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.ephilid.EphilfaqDto;
import com.msp.webportal.businesslogic.dto.faq.FaqCount;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import com.msp.webportal.businesslogic.response.Apiresponses;

public interface EphilfaqService {
    public EphilFaq save(EphilfaqDto ephilfaqDto);

    Apiresponses pageList(Pageable pageable);

    EphilFaq findByid(Long id);

    EphilFaq update(long id, EphilfaqDto ephilfaqDto);

    boolean deleteById(Long id);

    EphilfaqDto changeAnswerStatus(boolean status, long id);

    FaqCount mangaloreAnswerCount();
}
