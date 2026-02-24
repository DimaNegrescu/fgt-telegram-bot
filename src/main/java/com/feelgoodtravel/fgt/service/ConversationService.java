package com.feelgoodtravel.fgt.service;

import com.feelgoodtravel.fgt.entity.Lead;
import com.feelgoodtravel.fgt.enums.ConversationState;
import com.feelgoodtravel.fgt.util.BotMessageSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class ConversationService {

    private final LeadService leadService;
    private final BotMessageSender sender;
    private final LeadFinalizer leadFinalizer;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ConversationService(
            LeadService leadService,
            BotMessageSender sender,
            LeadFinalizer leadFinalizer
    ) {
        this.leadService = leadService;
        this.sender = sender;
        this.leadFinalizer = leadFinalizer;
    }

    public void handle(Long chatId, String text) {

        // 1️⃣ Tratăm comenzile Telegram
        if (text.startsWith("/")) {
            if (text.equals("/start")) {
                Lead lead = leadService.getOrCreate(chatId);
                lead.setState(ConversationState.DESTINATION); // setăm direct următoarea stare
                leadService.save(lead);
                sender.send(chatId, "Salut! Unde dorești să călătorești?");
                return;
            } else {
                sender.send(chatId, "Comandă necunoscută. Folosește /start pentru a începe.");
                return;
            }
        }

        // 2️⃣ Logica conversației pe stări
        Lead lead = leadService.getOrCreate(chatId);

        switch (lead.getState()) {
            case DESTINATION -> {
                lead.setDestination(text);
                sender.send(chatId, "Data plecării (DD.MM.YYYY)?");
                lead.setState(ConversationState.DATE);
            }

            case DATE -> {
                try {
                    LocalDate date = LocalDate.parse(text, DATE_FORMATTER);
                    lead.setTravelDate(date);
                    sender.send(chatId, "Durata vacanței");
                    lead.setState(ConversationState.DAYS_NUMBRS);
                } catch (DateTimeParseException e) {
                    sender.send(chatId, "Data nu este validă. Folosește formatul DD.MM.YYYY.");
                }
            }
            case DAYS_NUMBRS -> {
                lead.setDaysNumber(text);
                sender.send(chatId, "Câte persoane, inclusiv copii pînă la 12 ani?");
                lead.setState(ConversationState.PERSONS_ADULTS);
            }

            case PERSONS_ADULTS -> {
                try {
                    int persons = Integer.parseInt(text);
                    if (persons <= 0) throw new NumberFormatException();
                    lead.setPersonsAdults(persons);
                    sender.send(chatId, "Câți copii pînă la 12 ani??");
                    lead.setState(ConversationState.PERSONS_CHILDREN);
                } catch (NumberFormatException e) {
                    sender.send(chatId, "Te rog introdu un număr valid de persoane.");
                }
            }
            case PERSONS_CHILDREN -> {
                try {
                    int persons = Integer.parseInt(text);
                    if (persons < 0) throw new NumberFormatException();
                    lead.setPersonsChildren(persons);
                    sender.send(chatId, "Buget aproximativ?");
                    lead.setState(ConversationState.BUDGET);
                } catch (NumberFormatException e) {
                    sender.send(chatId, "Te rog introdu un număr valid de copii.");
                }
            }

            case BUDGET -> {
                lead.setBudget(text);
                sender.send(chatId, "Lasă un telefon sau email:");
                lead.setState(ConversationState.CONTACT);
            }

            case CONTACT -> {
                lead.setContact(text);
                lead.setState(ConversationState.DONE);
                leadService.save(lead);
                leadFinalizer.finalizeLead(lead);
                sender.send(chatId, "Mulțumim! Revenim cu ofertă în max. 24h.");
            }

            case DONE -> {
                sender.send(chatId, "Am primit deja datele. Un agent te va contacta.");
            }

            default -> {
                sender.send(chatId, "Eroare neașteptată. Folosește /start pentru a începe.");
                lead.setState(ConversationState.DESTINATION);
            }
        }

        leadService.save(lead);
    }
}

