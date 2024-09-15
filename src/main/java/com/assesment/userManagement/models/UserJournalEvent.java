package com.assesment.userManagement.models;

import lombok.Data;

@Data
public class UserJournalEvent {
    private Long userId;
    private String eventData;
}
