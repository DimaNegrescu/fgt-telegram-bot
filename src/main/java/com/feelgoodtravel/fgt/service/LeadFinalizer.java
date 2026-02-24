package com.feelgoodtravel.fgt.service;

import com.feelgoodtravel.fgt.entity.Lead;
import org.springframework.stereotype.Service;

@Service
public class LeadFinalizer {

    private final EmailService emailService;
    private final OperatorNotificationService operatorNotificationService;

    public LeadFinalizer(
            EmailService emailService,
            OperatorNotificationService operatorNotificationService
    ) {
        this.emailService = emailService;
        this.operatorNotificationService = operatorNotificationService;
    }

    public void finalizeLead(Lead lead) {
        emailService.sendLeadEmail(lead);
        operatorNotificationService.notifyNewLead(lead);
    }
}
