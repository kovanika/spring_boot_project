package com.example.spring_boot_project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailController {
    @Autowired
    private JavaMailSender sender;

    public void sendMessage(String to,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);

        sender.send(message);
    }
}