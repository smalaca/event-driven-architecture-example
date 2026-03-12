package com.smalaca.trainingmanagement.infrastructure;

import com.smalaca.shared.events.TrainingDraftApproved;
import com.smalaca.shared.events.TrainingDraftRejected;
import com.smalaca.trainingmanagement.domain.TrainingDraft;
import com.smalaca.trainingmanagement.domain.TrainingDraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingDraftReviewListener {
    private final TrainingDraftRepository repository;

    @KafkaListener(topics = "training-draft-approved", groupId = "training-management-group")
    public void onApproved(TrainingDraftApproved event) {
        repository.findById(event.trainingDraftId()).ifPresent(trainingDraft -> {
            trainingDraft.setStatus("APPROVED");
            repository.save(trainingDraft);
        });
    }

    @KafkaListener(topics = "training-draft-rejected", groupId = "training-management-group")
    public void onRejected(TrainingDraftRejected event) {
        repository.findById(event.trainingDraftId()).ifPresent(trainingDraft -> {
            trainingDraft.setStatus("REJECTED: " + event.reason());
            repository.save(trainingDraft);
        });
    }
}
