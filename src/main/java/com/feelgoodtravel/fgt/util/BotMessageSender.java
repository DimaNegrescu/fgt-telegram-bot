package com.feelgoodtravel.fgt.util;

import com.feelgoodtravel.fgt.dto.TravelBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotMessageSender {

    private final AbsSender sender;

    public BotMessageSender(AbsSender sender) {
        this.sender = sender;
    }

    public void send(Long chatId, String text) {
        SendMessage msg = new SendMessage(chatId.toString(), text);
        try {
            sender.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
