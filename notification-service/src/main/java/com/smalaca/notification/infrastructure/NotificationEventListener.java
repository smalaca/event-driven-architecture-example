package com.smalaca.notification.infrastructure;

import com.smalaca.notification.domain.SentEmail;
import com.smalaca.notification.domain.SentEmailRepository;
import com.smalaca.shared.events.TrainingDraftApproved;
import com.smalaca.shared.events.TrainingDraftRejected;
import com.smalaca.shared.events.TrainingDraftSubmitted;
import com.smalaca.shared.events.TrainingPublished;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final SentEmailRepository repository;

    @KafkaListener(topics = "training-draft-submitted", groupId = "notification-group")
    public void onSubmitted(TrainingDraftSubmitted event) {
        sendEmail(event.trainingDraftId(), "Training Draft Submitted: " + event.title());
    }

    @KafkaListener(topics = "training-draft-approved", groupId = "notification-group")
    public void onApproved(TrainingDraftApproved event) {
        sendEmail(event.trainingDraftId(), "Training Draft Approved");
    }

    @KafkaListener(topics = "training-draft-rejected", groupId = "notification-group")
    public void onRejected(TrainingDraftRejected event) {
        sendEmail(event.trainingDraftId(), "Training Draft Rejected: " + event.reason());
    }

    @KafkaListener(topics = "training-published", groupId = "notification-group")
    public void onPublished(TrainingPublished event) {
        sendEmail(event.trainingDraftId(), "Training Published: " + event.title());
    }

    private void sendEmail(UUID trainingDraftId, String subject) {
        String recipient = "user@example.com";
        String content = "Hello! " + subject;
        repository.save(new SentEmail(trainingDraftId, recipient, subject, content));
    }
}
