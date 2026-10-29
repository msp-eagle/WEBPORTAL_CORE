package com.msp.webportal.businesslogic.service.faq;


import com.msp.webportal.businesslogic.converter.faq.FaqQuestionDtoToQuestion;
import com.msp.webportal.businesslogic.converter.faq.FaqQuestionToFaqQuestionAudit;
import com.msp.webportal.businesslogic.converter.faq.FaqQuestionToFaqQuestionDto;
import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.faq.FaqQuestionDto;
import com.msp.webportal.businesslogic.model.faq.FaqQuestion;
import com.msp.webportal.businesslogic.model.faq.FaqQuestionAudit;
import com.msp.webportal.businesslogic.repository.faq.FaqQuestionAuditRepository;
import com.msp.webportal.businesslogic.repository.faq.FaqQuestionRepo;
import com.msp.webportal.businesslogic.response.Apiresponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FaqQuestionServiceimpl implements FaqQuestionService {
    @Autowired
    private FaqQuestionRepo faqQuestionRepo;
    @Autowired
    private FaqQuestionAuditRepository faqQuestionAuditRepository;

    @Autowired
    private FaqQuestionDtoToQuestion faqQuestionDtoToQuestion;
    @Autowired
    private FaqQuestionToFaqQuestionAudit faqQuestionToFaqQuestionAudit;

    @Autowired
    private FaqQuestionToFaqQuestionDto faqQuestionToFaqQuestionDto;

    @Override
    public FaqQuestion save(FaqQuestionDto faqQuestionDto) {

        FaqQuestion faqQuestion = faqQuestionDtoToQuestion.convert(faqQuestionDto);
        faqQuestion.setCreatedDate(LocalDate.now());
        FaqQuestion saveQuestion = faqQuestionRepo.save(faqQuestion);
        FaqQuestionAudit faqQuestionAudit = faqQuestionToFaqQuestionAudit.convert(saveQuestion);
        FaqQuestionAudit saveQuestionAudit = faqQuestionAuditRepository.save(faqQuestionAudit);

        return saveQuestion;
    }

    @Override
    public Apiresponses pageList(Pageable pageable) {
        Apiresponses apiresponses = new Apiresponses();
        Long totalCount = faqQuestionRepo.count();
        List<FaqQuestionDto> list = new ArrayList<>();
        faqQuestionRepo.findAll(PageRequest.of(pageable.getStart(), pageable.getSize(), Sort.by("createdDate").descending())).stream().forEach(
                device -> list.add(faqQuestionToFaqQuestionDto.convert(device)));
        apiresponses.setStatus(true);
        apiresponses.setTotalRecords(totalCount);
        apiresponses.setData(list);
        return apiresponses;
    }

    @Override
    public FaqQuestion findByid(Long id) {
        Optional<FaqQuestion> optionalFaqQuestion = faqQuestionRepo.findById(id);
        return optionalFaqQuestion.get();
    }

    @Override
    public FaqQuestion update(long id, FaqQuestionDto faqQuestionDto) {
        Optional<FaqQuestion> optionalFaqQuestion = faqQuestionRepo.findById(id);
        if(!optionalFaqQuestion.isPresent()){
//            throw new ResourceNotFoundExceptions("User id Not Found", "Id", id);
        }
        FaqQuestion faqQuestion = optionalFaqQuestion.get();
        FaqQuestion updatedData =  faqQuestionDtoToQuestion.convert(faqQuestionDto);
        faqQuestion.setId(id);
        faqQuestion.setQuestion(updatedData.getQuestion());
        faqQuestion.setCreatedDate(updatedData.getCreatedDate());
        faqQuestion.setStatus(updatedData.isStatus());

        FaqQuestion modifyUser = faqQuestionRepo.save(faqQuestion);
        if (modifyUser !=null){
            FaqQuestionAudit faqQuestionAudit = faqQuestionToFaqQuestionAudit.convert(modifyUser);
            FaqQuestionAudit savefaqQuestionAudit = faqQuestionAuditRepository.save(faqQuestionAudit);
        }


        return modifyUser;
    }

    @Override
    public boolean deleteById(Long id) {
        faqQuestionRepo.deleteById(id);
        Optional<FaqQuestion> faqQuestion = faqQuestionRepo.findById(id);
        return !faqQuestion.isPresent();
    }
}
