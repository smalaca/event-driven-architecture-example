package com.smalaca.notification.api;

import com.smalaca.notification.domain.SentEmail;
import com.smalaca.notification.domain.SentEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final SentEmailRepository repository;

    @GetMapping("/{trainingDraftId}")
    public List<SentEmail> getSentEmails(@PathVariable UUID trainingDraftId) {
        return repository.findByTrainingDraftId(trainingDraftId);
    }

    @GetMapping
    public Iterable<SentEmail> findAll() {
        return repository.findAll();
    }
}
