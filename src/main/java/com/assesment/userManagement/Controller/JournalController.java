package com.assesment.userManagement.Controller;

import com.assesment.userManagement.kafkaProducer.JournalProducer;
import com.assesment.userManagement.models.UserJournalEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/journal")
public class JournalController {

    @Autowired
    private JournalProducer journalProducer;

    @PostMapping
    @Transactional
    public void saveJournal(@RequestParam String journal,
                            @RequestParam Long id){
        UserJournalEvent userJournalEvent = new UserJournalEvent();
        userJournalEvent.setUserId(id);
        userJournalEvent.setEventData(journal);
        journalProducer.sendUserJournalEvent(userJournalEvent);
    }
}
