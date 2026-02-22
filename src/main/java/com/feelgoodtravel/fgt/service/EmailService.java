package com.feelgoodtravel.fgt.service;

import com.feelgoodtravel.fgt.entity.Lead;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendLeadEmail(Lead lead) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("negrescudumitru@yahoo.com");
        message.setSubject("🧳 Solicitare noua din Telegram");

        message.setText("""
            Destinație: %s
            Data plecării: %s
            Numarul de zile: %s
            Persoane mature: %d
            Copii: %d
            Buget: %s
            Contact: %s
            Telegram Chat ID: %d
            """.formatted(
                lead.getDestination(),
                lead.getTravelDate(),
                lead.getDaysNumber(),
                lead.getPersonsAdults(),
                lead.getPersonsChildren(),
                lead.getBudget(),
                lead.getContact(),
                lead.getChatId()
        ));

        mailSender.send(message);
    }
}

