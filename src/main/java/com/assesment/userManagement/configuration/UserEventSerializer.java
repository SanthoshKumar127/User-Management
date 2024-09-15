package com.assesment.userManagement.configuration;


import com.assesment.userManagement.models.UserJournalEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserEventSerializer implements Serializer<UserJournalEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String
            s, UserJournalEvent eventType) {
        return null;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserJournalEvent data) {
        try {
            String jsonString = objectMapper.writeValueAsString(data);
            return jsonString.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize UserJournalEvent: " + e.getMessage());
        }
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
