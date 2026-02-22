package com.feelgoodtravel.fgt.util;

import com.feelgoodtravel.fgt.dto.TravelBot;
import com.feelgoodtravel.fgt.service.ConversationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Bean
    public TravelBot travelBot(@Lazy ConversationService conversationService) {
        return new TravelBot(conversationService);
    }

    @Primary
    @Bean
    public AbsSender absSender(TravelBot travelBot) {
        return travelBot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(TravelBot travelBot) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(travelBot);
        return botsApi;
    }
}

