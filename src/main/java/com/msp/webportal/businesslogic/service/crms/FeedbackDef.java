package com.msp.webportal.businesslogic.service.crms;

import com.msp.webportal.businesslogic.model.crms.Feedback;
import com.msp.webportal.businesslogic.repository.crms.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackDef implements FeedbackDecal{

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public void saveData(Feedback feedback) {
System.out.println("feedback:"+ feedback.toString());
        feedbackRepository.save(feedback);

    }

}
