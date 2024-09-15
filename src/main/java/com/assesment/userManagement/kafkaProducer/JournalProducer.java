package com.assesment.userManagement.kafkaProducer;

import com.assesment.userManagement.models.UserJournalEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JournalProducer {

    @Autowired
    private KafkaTemplate<String, UserJournalEvent> kafkaTemplate;

    public void sendUserJournalEvent(UserJournalEvent userJournalEvent){
        kafkaTemplate.send("event-journal-test", userJournalEvent);
    }
}
