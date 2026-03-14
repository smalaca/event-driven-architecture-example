package com.smalaca.applicationmanagement.api;

import com.smalaca.shared.events.ApplicationDraftSubmitted;
import com.smalaca.applicationmanagement.domain.ApplicationDraft;
import com.smalaca.applicationmanagement.domain.ApplicationDraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/application-drafts")
@RequiredArgsConstructor
public class ApplicationManagementController {
    private final ApplicationDraftRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public UUID submit(@RequestBody ApplicationDraftRequest request) {
        UUID id = UUID.randomUUID();
        ApplicationDraft applicationDraft = new ApplicationDraft(id, request.title(), request.description(), request.price(), "SUBMITTED");
        repository.save(applicationDraft);

        kafkaTemplate.send("application-draft-submitted", new ApplicationDraftSubmitted(id, request.title(), request.description(), request.price()));

        return id;
    }

    @GetMapping("/{id}")
    public ApplicationDraft getStatus(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<ApplicationDraft> findAll() {
        return repository.findAll();
    }
}

record ApplicationDraftRequest(String title, String description, BigDecimal price) { }
