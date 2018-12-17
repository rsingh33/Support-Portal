package com.citi.spring.web.service;

import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.emailHandler.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emailService")
public class EmailService {

    @Autowired
    private HandoverDao handoverDAO;

    @Scheduled(cron = "0 0 5,12,17 ? * *")
    public void emailScheduler() {
        System.out.println("Email sent!!!!!");
        List<Handover> handovers = handoverDAO.getHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        try {
            SendEmail.emailSend(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   }
