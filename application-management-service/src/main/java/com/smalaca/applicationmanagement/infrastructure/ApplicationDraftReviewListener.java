package com.smalaca.applicationmanagement.infrastructure;

import com.smalaca.shared.events.ApplicationDraftApproved;
import com.smalaca.shared.events.ApplicationDraftRejected;
import com.smalaca.shared.events.ApplicationPublished;
import com.smalaca.applicationmanagement.domain.ApplicationDraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationDraftReviewListener {
    private final ApplicationDraftRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "application-draft-approved", groupId = "application-management-group")
    public void onApproved(ApplicationDraftApproved event) {
        repository.findById(event.applicationDraftId()).ifPresent(applicationDraft -> {
            applicationDraft.setStatus("APPROVED");
            repository.save(applicationDraft);
            kafkaTemplate.send("application-published", new ApplicationPublished(applicationDraft.getId(), applicationDraft.getTitle(), applicationDraft.getDescription(), applicationDraft.getPrice()));
        });
    }

    @KafkaListener(topics = "application-draft-rejected", groupId = "application-management-group")
    public void onRejected(ApplicationDraftRejected event) {
        repository.findById(event.applicationDraftId()).ifPresent(applicationDraft -> {
            applicationDraft.setStatus("REJECTED: " + event.reason());
            repository.save(applicationDraft);
        });
    }
}
