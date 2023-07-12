package com.example.spring_boot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyEmailService {
    @Autowired
    private JavaMailSender sender;
    public void sendMessage(String text, String email){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setText(text);

        sender.send(message);
    }
}
