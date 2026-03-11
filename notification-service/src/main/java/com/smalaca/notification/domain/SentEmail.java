package com.smalaca.notification.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SentEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID trainingDraftId;
    private String recipient;
    private String subject;
    private String content;

    public SentEmail(UUID trainingDraftId, String recipient, String subject, String content) {
        this.trainingDraftId = trainingDraftId;
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
}
