package com.haiph.userservice.service.impl.sercurity;

import com.haiph.userservice.service.impl.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;


    public String sendMail(String emailRevice, String subject, String message) {
        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailRevice);
            mailMessage.setText(message);
            mailMessage.setSubject(subject);


            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    public String sendMail2(String emailRevice, String subject, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(sender);
            messageHelper.setTo(emailRevice);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };
        javaMailSender.send(messagePreparator);
        return "SendMail Success";
    }

}
