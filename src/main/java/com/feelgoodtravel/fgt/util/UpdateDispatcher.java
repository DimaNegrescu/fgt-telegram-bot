//package com.feelgoodtravel.fgt.util;
//
//import com.feelgoodtravel.fgt.service.ConversationService;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@Component
//public class UpdateDispatcher {
//
//    private final ConversationService conversationService;
//
//    public UpdateDispatcher(ConversationService conversationService) {
//        this.conversationService = conversationService;
//    }
//
//    public void dispatch(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            conversationService.handle(
//                    update.getMessage().getChatId(),
//                    update.getMessage().getText()
//            );
//        }
//    }
//}
//
