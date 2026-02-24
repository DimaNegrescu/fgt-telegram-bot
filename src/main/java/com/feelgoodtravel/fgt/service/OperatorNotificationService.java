package com.feelgoodtravel.fgt.service;

import com.feelgoodtravel.fgt.entity.Lead;
import com.feelgoodtravel.fgt.util.BotMessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OperatorNotificationService {

    private final Long operatorChatId;
    private final BotMessageSender messageSender;

    public OperatorNotificationService(
            @Value("${telegram.operator-chat-id}") Long operatorChatId,
            BotMessageSender messageSender
    ) {
        this.operatorChatId = operatorChatId;
        this.messageSender = messageSender;
    }

    public void notifyNewLead(Lead lead) {

        String message = """
        🧳 Lead nou – FeelGoodTravel

        📍 Destinație: %s
        📅 Data plecării: %s
        🗓️ Numărul de zile: %s
        👤 Persoane mature: %d
        👶 Copii: %d
        💰 Buget: %s
        📞 Contact: %s
        🆔 Telegram Chat ID: %d
        """.formatted(
                lead.getDestination() != null ? lead.getDestination() : "—",
                lead.getTravelDate() != null ? lead.getTravelDate() : "—",
                lead.getDaysNumber() != null ? lead.getDaysNumber() : "—",
                lead.getPersonsAdults() != null ? lead.getPersonsAdults() : 0,
                lead.getPersonsChildren() != null ? lead.getPersonsChildren() : 0,
                lead.getBudget() != null ? lead.getBudget() : "—",
                lead.getContact() != null ? lead.getContact() : "—",
                lead.getChatId()
        );

        messageSender.send(operatorChatId, message);
    }
}
