package com.feelgoodtravel.fgt.service;

import com.feelgoodtravel.fgt.entity.Lead;
import com.feelgoodtravel.fgt.enums.ConversationState;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LeadService {

    private final Map<Long, Lead> storage = new HashMap<>();

    public Lead getOrCreate(Long chatId) {
        return storage.computeIfAbsent(chatId, id -> {
            Lead lead = new Lead();
            lead.setChatId(chatId);
            lead.setState(ConversationState.START);
            return lead;
        });
    }

    public void save(Lead lead) {
        storage.put(lead.getChatId(), lead);
    }

    public void reset(Long chatId) {
        storage.remove(chatId);
    }
}

