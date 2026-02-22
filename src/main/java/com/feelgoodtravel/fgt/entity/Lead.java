package com.feelgoodtravel.fgt.entity;

import com.feelgoodtravel.fgt.enums.ConversationState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Lead {

    @Id
    private Long chatId;
    private String destination;
    private LocalDate travelDate;
    private Integer personsAdults;
    private Integer personsChildren;
    private String daysNumber;
    private String budget;
    private String contact;

    @Enumerated(EnumType.STRING)
    private ConversationState state;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getPersonsAdults() {
        return personsAdults;
    }

    public void setPersonsAdults(Integer personsAdults) {
        this.personsAdults = personsAdults;
    }

    public Integer getPersonsChildren() {
        return personsChildren;
    }

    public void setPersonsChildren(Integer personsChildren) {
        this.personsChildren = personsChildren;
    }

    public String getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(String daysNumber) {
        this.daysNumber = daysNumber;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ConversationState getState() {
        return state;
    }

    public void setState(ConversationState state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
