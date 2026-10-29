package com.msp.webportal.businesslogic.service.home;//package com.service.philyps.service.home;
//
//import com.service.philyps.commonUtil.UniqueIdGenerator;
//import com.service.philyps.dto.CRMSDto;
//import com.service.philyps.model.crms.CRMSEntity;
//import com.service.philyps.repository.home.CRMSRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CRMSService {
//
//    @Autowired
//    private CRMSRepository crmsRepository;
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
////    public CRMSDto saveUserFeedback(CRMSEntity crmsEntity){
//        String complientID = UniqueIdGenerator.generateID();
//        crmsEntity.setId(complientID);
//        crmsEntity.setStatus("Inprogress");
//        CRMSDto crmsDto = new CRMSDto();
//        try {
//            crmsRepository.save(crmsEntity);
//            crmsDto.setComplientId(complientID);
//            crmsDto.setStatus("SUCCESS");
//        }catch (Exception e){
//            logger.error("Contact Insert: " + e.getMessage());
//            crmsDto.setStatus("FAILED");
//        }
//        return crmsDto;
//    }
//}
