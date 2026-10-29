package com.msp.webportal.businesslogic.service.ephilid;

import com.msp.webportal.businesslogic.converter.ephil.EphilFaqDtoToEphilFaq;
import com.msp.webportal.businesslogic.converter.ephil.EphilFaqToEphilFaqAudit;
import com.msp.webportal.businesslogic.converter.ephil.EphilFaqToEphilFaqDto;
import com.msp.webportal.businesslogic.dto.Pageable;
import com.msp.webportal.businesslogic.dto.ephilid.EphilfaqDto;
import com.msp.webportal.businesslogic.dto.faq.FaqCount;
import com.msp.webportal.businesslogic.exception.ResourceNotFound;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaq;
import com.msp.webportal.businesslogic.model.ephilid.EphilFaqAudit;
import com.msp.webportal.businesslogic.repository.ephilid.EphilFaqRepo;
import com.msp.webportal.businesslogic.repository.ephilid.EphilfaqAuditRepo;
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
public class EphilFaqServiceimpl implements EphilfaqService {
    @Autowired
    private EphilFaqRepo ephilFaqRepo;
    @Autowired
    private EphilfaqAuditRepo ephilfaqAuditRepo;
    @Autowired
    private EphilFaqToEphilFaqDto faqAnswerToFaqAnswerDto;
    @Autowired
    private EphilFaqDtoToEphilFaq faqAnswerDtoToFaqAnswer;
    @Autowired
    private EphilFaqToEphilFaqAudit ephilFaqToEphilFaqAudit;

    @Override
    public EphilFaq save(EphilfaqDto faqAnswerDto) {


        EphilFaq faqAnswer = faqAnswerDtoToFaqAnswer.convert(faqAnswerDto);
        System.out.println("TEMPORARY MESSAGE");
        System.out.println(faqAnswer);
        faqAnswer.setCreatedDate(LocalDateTime.now());
        faqAnswer.setFaqCollapse(faqAnswer.getFaqid());
        faqAnswer.setFaqid("#"+faqAnswer.getFaqid());
        EphilFaq saveAnswer = ephilFaqRepo.save(faqAnswer);
        EphilFaqAudit faqQuestionAudit = ephilFaqToEphilFaqAudit.convert(saveAnswer);
        System.out.println("TEMPORARY MESSAGE");
        System.out.println(faqQuestionAudit);
        EphilFaqAudit saveAnswerAudit = ephilfaqAuditRepo.save(faqQuestionAudit);

        return saveAnswer;

    }

    @Override
    public Apiresponses pageList(Pageable pageable) {
        Apiresponses apiresponses = new Apiresponses();
        Long totalCount = ephilFaqRepo.count();
        List<EphilfaqDto> list = new ArrayList<>();
        ephilFaqRepo.findAll(PageRequest.of(pageable.getStart(), pageable.getSize(), Sort.by("createdDate").descending())).stream().forEach(
                device -> list.add(faqAnswerToFaqAnswerDto.convert(device)));
        apiresponses.setStatus(true);
        apiresponses.setTotalRecords(totalCount);
        apiresponses.setData(list);
        return apiresponses;
    }

    @Override
    public EphilFaq findByid(Long id) {
        Optional<EphilFaq> optionalFaqAnswer = ephilFaqRepo.findById(id);
        return optionalFaqAnswer.get();
    }


    @Override
    public EphilFaq update(long id, EphilfaqDto ephilfaqDto) {
        Optional<EphilFaq> optionalFaqAnswer = ephilFaqRepo.findById(id);
        if(!optionalFaqAnswer.isPresent()){
//            throw new ResourceNotFoundException("User id Not Found", "Id", id);
        }
        EphilFaq faqAnswer = optionalFaqAnswer.get();
        EphilFaq updatedData =  faqAnswerDtoToFaqAnswer.convert(ephilfaqDto);
        faqAnswer.setId(id);
        faqAnswer.setQuestion(updatedData.getQuestion());
        faqAnswer.setAnswer(updatedData.getAnswer());
        faqAnswer.setCreatedDate(LocalDateTime.now());
        faqAnswer.setStatus(updatedData.isStatus());

        EphilFaq modifyUser = ephilFaqRepo.save(faqAnswer);
        if (modifyUser !=null){
            EphilFaqAudit faqAnswerAudit = ephilFaqToEphilFaqAudit.convert(modifyUser);
            EphilFaqAudit saveAnswerAudit = ephilfaqAuditRepo.save(faqAnswerAudit);
        }


        return modifyUser;
    }



    @Override
    public boolean deleteById(Long id) {
        ephilFaqRepo.deleteById(id);
        Optional<EphilFaq> faqAnswer = ephilFaqRepo.findById(id);
        return !faqAnswer.isPresent();
    }

    @Override
    public EphilfaqDto changeAnswerStatus(boolean status, long id) {
        Optional<EphilFaq> optionalFaqAnswer = ephilFaqRepo.findById(id);
        if(optionalFaqAnswer.isPresent()){
            EphilFaq AnswerData = optionalFaqAnswer.get();
            AnswerData.setStatus(status);

            EphilFaq answerUpdate = ephilFaqRepo.save(AnswerData);

            EphilFaqAudit AnswerAuditUpdate = ephilfaqAuditRepo.save(ephilFaqToEphilFaqAudit.convert(answerUpdate));
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
        Long count1=ephilFaqRepo.findCount();
        Long count2=ephilFaqRepo.findbyWeek();
        Long count3=ephilFaqRepo.findbyMonth();
        faqCount.setCount(count1);
        faqCount.setWeeklycount(count2);
        faqCount.setMonthlycount(count3);
        return faqCount;
    }
}
