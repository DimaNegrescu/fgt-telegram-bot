package com.feelgoodtravel.fgt.dto;

import com.feelgoodtravel.fgt.service.ConversationService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TravelBot extends TelegramLongPollingBot {

    private final ConversationService conversationService;

    public TravelBot(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            conversationService.handle(
                    update.getMessage().getChatId(),
                    update.getMessage().getText()
            );
        }
    }

    @Override
    public String getBotUsername() {
        return "feelgood_travel_bot";
    }

    @Override
    public String getBotToken() {
        return " satu";
    }
}