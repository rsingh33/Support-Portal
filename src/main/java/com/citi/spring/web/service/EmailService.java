package com.citi.spring.web.service;

import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.emailHandler.ListToHtmlTransformer;
import com.citi.spring.web.emailHandler.SendEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class EmailService {

    private static Logger logger = Logger.getLogger(EmailService.class);

    @Autowired
    private HandoverDao handoverDao;

    @Scheduled(cron = "0 0 5,12,17 ? * *")
    public void emailScheduler() {

        List<Handover> handovers = handoverDao.getHandover();
        String content = ListToHtmlTransformer.compose(handovers);
        System.out.println(content);

        try {
            SendEmail.emailSend(content);
        } catch (Exception ex) {
            logger.error("Scheduled email can't be sent " + ex.getCause() + ex.getClass(), ex);
        }

    }

    @Async
    public void emailSend(String content, String to, String subject) throws Exception {

        SendEmail.emailSend(content, to, subject);

    }

    @Async
    public void emailSend(String content, Set<String> to, String subject) throws Exception {

        SendEmail.emailSend(content, to, subject);

    }

    @Async
    public void emailSend(String content) throws Exception {
        SendEmail.emailSend(content);
    }
}
