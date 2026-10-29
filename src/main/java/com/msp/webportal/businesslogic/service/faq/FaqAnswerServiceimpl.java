package com.msp.webportal.businesslogic.service.faq;


import com.msp.webportal.businesslogic.converter.faq.FaqAnswerDtoToFaqAnswer;
import com.msp.webportal.businesslogic.converter.faq.FaqAnswerToFaqAnswerAudit;
import com.msp.webportal.businesslogic.converter.faq.FaqAnswerToFaqAnswerDto;
import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.faq.FaqAnswerDto;
import com.msp.webportal.businesslogic.dto.faq.FaqCount;
import com.msp.webportal.businesslogic.exception.ResourceNotFound;
import com.msp.webportal.businesslogic.model.faq.FaqAnswer;
import com.msp.webportal.businesslogic.model.faq.FaqAnswerAudit;
import com.msp.webportal.businesslogic.repository.faq.FaqAnswerAuditRepository;
import com.msp.webportal.businesslogic.repository.faq.FaqAnswerRepo;
import com.msp.webportal.businesslogic.response.Apiresponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FaqAnswerServiceimpl implements FaqAnswerService {
    @Autowired
    private FaqAnswerRepo faqAnswerRepo;
    @Autowired
    private FaqAnswerAuditRepository faqAnswerAuditRepository;
    @Autowired
    private FaqAnswerToFaqAnswerDto faqAnswerToFaqAnswerDto;
    @Autowired
    private FaqAnswerDtoToFaqAnswer faqAnswerDtoToFaqAnswer;
    @Autowired
    private FaqAnswerToFaqAnswerAudit faqAnswerToFaqAnswerAudit;

    @Override
    public FaqAnswer save(FaqAnswerDto faqAnswerDto) {


        FaqAnswer faqAnswer = faqAnswerDtoToFaqAnswer.convert(faqAnswerDto);
        faqAnswer.setCreatedDate(LocalDateTime.now());
        faqAnswer.setFaqCollapse(faqAnswer.getFaqid());
        faqAnswer.setTopics(faqAnswer.getTopics());
        faqAnswer.setFaqid("#"+faqAnswer.getFaqid());
        FaqAnswer saveAnswer = faqAnswerRepo.save(faqAnswer);
        FaqAnswerAudit faqQuestionAudit = faqAnswerToFaqAnswerAudit.convert(saveAnswer);
        FaqAnswerAudit saveAnswerAudit = faqAnswerAuditRepository.save(faqQuestionAudit);

        return saveAnswer;

    }

    @Override
    public Apiresponses pageList(Pageable pageable) {
        Apiresponses apiresponses = new Apiresponses();
        Long totalCount = faqAnswerRepo.count();
        List<FaqAnswerDto> list = new ArrayList<>();
        faqAnswerRepo.findAll(PageRequest.of(pageable.getStart(), pageable.getSize(), Sort.by("createdDate").descending())).stream().forEach(
                device -> list.add(faqAnswerToFaqAnswerDto.convert(device)));
        apiresponses.setStatus(true);
        apiresponses.setTotalRecords(totalCount);
        apiresponses.setData(list);
        return apiresponses;
    }

    @Override
    public FaqAnswer findByid(Long id) {
        Optional<FaqAnswer> optionalFaqAnswer = faqAnswerRepo.findById(id);
        return optionalFaqAnswer.get();
    }

    @Override
    public FaqAnswer update(long id, FaqAnswerDto faqAnswerDto) {
        Optional<FaqAnswer> optionalFaqAnswer = faqAnswerRepo.findById(id);
        if(!optionalFaqAnswer.isPresent()){
//            throw new ResourceNotFoundException("User id Not Found", "Id", id);
        }
        FaqAnswer faqAnswer = optionalFaqAnswer.get();
        FaqAnswer updatedData =  faqAnswerDtoToFaqAnswer.convert(faqAnswerDto);
        faqAnswer.setId(id);
        faqAnswer.setQuestion(updatedData.getQuestion());
        faqAnswer.setAnswer(updatedData.getAnswer());
        faqAnswer.setCreatedDate(LocalDateTime.now());
        faqAnswer.setStatus(updatedData.isStatus());

        FaqAnswer modifyUser = faqAnswerRepo.save(faqAnswer);
        if (modifyUser !=null){
            FaqAnswerAudit faqAnswerAudit = faqAnswerToFaqAnswerAudit.convert(modifyUser);
            FaqAnswerAudit saveAnswerAudit = faqAnswerAuditRepository.save(faqAnswerAudit);
        }


        return modifyUser;
    }

    @Override
    public boolean deleteById(Long id) {
        faqAnswerRepo.deleteById(id);
        Optional<FaqAnswer> faqAnswer = faqAnswerRepo.findById(id);
        return !faqAnswer.isPresent();
    }

    @Override
    public FaqAnswerDto changeAnswerStatus(boolean status, long id) {
        Optional<FaqAnswer> optionalFaqAnswer = faqAnswerRepo.findById(id);
        if(optionalFaqAnswer.isPresent()){
            FaqAnswer AnswerData = optionalFaqAnswer.get();
            AnswerData.setStatus(status);

            FaqAnswer answerUpdate = faqAnswerRepo.save(AnswerData);

            FaqAnswerAudit AnswerAuditUpdate = faqAnswerAuditRepository.save(faqAnswerToFaqAnswerAudit.convert(answerUpdate));
            if(AnswerAuditUpdate != null){
                return  faqAnswerToFaqAnswerDto.convert(answerUpdate);
            }else{
                throw new ResourceNotFound(false, "Answer Status Not Updated", String.format("Unable to Update Data"));
            }
        }else{
            throw new ResourceNotFound(false, "Answer Status Not Found", String.format("Unable to find Answer Id %s",id));

        }
    }

    @Override
    public FaqCount mangaloreAnswerCount() {
        FaqCount faqCount=new FaqCount();
        Long count1=faqAnswerRepo.findCount();
        Long count2=faqAnswerRepo.findbyWeek();
        Long count3=faqAnswerRepo.findbyMonth();
        faqCount.setCount(count1);
        faqCount.setWeeklycount(count2);
        faqCount.setMonthlycount(count3);
        return faqCount;
    }
}
