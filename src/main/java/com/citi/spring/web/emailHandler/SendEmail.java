package com.citi.spring.web.emailHandler;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class SendEmail {

    static DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
   static  Date date = new Date();


    public static void emailSend(String content) throws Exception {

        ReadProperties.readConfig();
        Properties props = new Properties();

        props.put("mail.smtp.host", Constants.smtpHost);
        props.put("mail.smtp.port", Constants.smtpPort);
        //    props.put("mail.smtp.auth", Constants.smtpAuth);
        //   props.put("mail.smtp.starttls.enable", Constants.smtpTLS);

        Session session = Session.getDefaultInstance(props, null);

        try {

            String[] tolist = Constants.emailTO.split(";");
            InternetAddress[] address;
            address = new InternetAddress[tolist.length];
            for (int i = 0; i < tolist.length; i++) {
                InternetAddress addresses = new InternetAddress(tolist[i]);
                address[i] = addresses;
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constants.setFrom));
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(Constants.emailSubject + "  " + dateFormat.format(date));
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Email could not be sent ");
           e.printStackTrace();
        }

    }

    public static void emailSend(String content,  String to, String subject) throws Exception {

        ReadProperties.readConfig();
        Properties props = new Properties();

        props.put("mail.smtp.host", Constants.smtpHost);
        props.put("mail.smtp.port", Constants.smtpPort);
        //    props.put("mail.smtp.auth", Constants.smtpAuth);
        //   props.put("mail.smtp.starttls.enable", Constants.smtpTLS);

        Session session = Session.getDefaultInstance(props, null);

        try {

            String[] tolist = to.split(";");
            InternetAddress[] address;
            address = new InternetAddress[tolist.length];
            for (int i = 0; i < tolist.length; i++) {
                InternetAddress addresses = new InternetAddress(tolist[i]);
                address[i] = addresses;
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constants.setFrom));
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject + "  " + dateFormat.format(date));
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Email could not be sent ");
            e.printStackTrace();
        }

    }
}