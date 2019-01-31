package com.citi.spring.web.service;

import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.emailHandler.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("emailService")
public class EmailService {

    @Qualifier("handoverDao")
    @Autowired
    private HandoverDao handoverDAO;

    @Scheduled(cron = "0 0 5,12,17 ? * *")
    public void emailScheduler() throws Exception {

        List<Handover> handovers = handoverDAO.getHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        System.out.println(content);

            SendEmail.emailSend(content);

        System.out.println("Email sent!!!!!");
    }
    @Async
   public void emailSend(String content, String to, String subject) throws Exception{

           SendEmail.emailSend(content,to,subject);

   }

    @Async
    public void emailSend(String content, Set<String> to, String subject) throws Exception{

        SendEmail.emailSend(content, to, subject);

    }
    @Async
    public  void emailSend(String content) throws Exception{
        SendEmail.emailSend(content);
    }
   }
