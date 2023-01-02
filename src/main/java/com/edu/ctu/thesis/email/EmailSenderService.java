package com.edu.ctu.thesis.email;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(EmailEntity email) {
        if(!email.isValid()) {
            throw new IllegalArgumentException("Invalid email input");
        }
        log.info("Sending email to [{}] ...", email.getTo());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setCc(email.getCc());
        message.setBcc(email.getBcc());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        // message.

        mailSender.send(message);
        log.info("Sent email succeed");
    }

}
