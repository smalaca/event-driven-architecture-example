package com.smalaca.notification.infrastructure;

import com.smalaca.notification.domain.SentEmail;
import com.smalaca.notification.domain.SentEmailRepository;
import com.smalaca.shared.events.ApplicationDraftApproved;
import com.smalaca.shared.events.ApplicationDraftRejected;
import com.smalaca.shared.events.ApplicationDraftSubmitted;
import com.smalaca.shared.events.ApplicationPublished;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final SentEmailRepository repository;

    @KafkaListener(topics = "application-draft-submitted", groupId = "notification-group")
    public void onSubmitted(ApplicationDraftSubmitted event) {
        sendEmail(event.applicationDraftId(), "Application Draft Submitted: " + event.title());
    }

    @KafkaListener(topics = "application-draft-approved", groupId = "notification-group")
    public void onApproved(ApplicationDraftApproved event) {
        sendEmail(event.applicationDraftId(), "Application Draft Approved");
    }

    @KafkaListener(topics = "application-draft-rejected", groupId = "notification-group")
    public void onRejected(ApplicationDraftRejected event) {
        sendEmail(event.applicationDraftId(), "Application Draft Rejected: " + event.reason());
    }

    @KafkaListener(topics = "application-published", groupId = "notification-group")
    public void onPublished(ApplicationPublished event) {
        sendEmail(event.applicationDraftId(), "Application Published: " + event.title());
    }

    private void sendEmail(UUID applicationDraftId, String subject) {
        String recipient = "user@example.com";
        String content = "Hello! " + subject;
        repository.save(new SentEmail(applicationDraftId, recipient, subject, content));
    }
}
