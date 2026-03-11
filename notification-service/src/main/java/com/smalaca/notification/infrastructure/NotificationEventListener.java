package com.smalaca.notification.infrastructure;

import com.smalaca.shared.events.TrainingDraftApproved;
import com.smalaca.shared.events.TrainingDraftRejected;
import com.smalaca.shared.events.TrainingDraftSubmitted;
import com.smalaca.shared.events.TrainingPublished;
import com.smalaca.notification.domain.SentEmail;
import com.smalaca.notification.domain.SentEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventListener {
    private final SentEmailRepository repository;

    @KafkaListener(topics = "training-draft-submitted", groupId = "notification-group")
    public void onSubmitted(TrainingDraftSubmitted event) {
        sendEmail(event.getTrainingDraftId(), "Training Draft Submitted: " + event.getTitle());
    }

    @KafkaListener(topics = "training-draft-approved", groupId = "notification-group")
    public void onApproved(TrainingDraftApproved event) {
        sendEmail(event.getTrainingDraftId(), "Training Draft Approved");
    }

    @KafkaListener(topics = "training-draft-rejected", groupId = "notification-group")
    public void onRejected(TrainingDraftRejected event) {
        sendEmail(event.getTrainingDraftId(), "Training Draft Rejected: " + event.getReason());
    }

    @KafkaListener(topics = "training-published", groupId = "notification-group")
    public void onPublished(TrainingPublished event) {
        sendEmail(event.getTrainingDraftId(), "Training Published: " + event.getTitle());
    }

    private void sendEmail(java.util.UUID trainingDraftId, String subject) {
        String recipient = "user@example.com";
        String content = "Hello! " + subject;
        log.info("Sending email to {}: {}", recipient, subject);
        repository.save(new SentEmail(trainingDraftId, recipient, subject, content));
    }
}
