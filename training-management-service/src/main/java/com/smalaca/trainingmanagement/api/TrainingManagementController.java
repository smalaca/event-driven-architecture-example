package com.smalaca.trainingmanagement.api;

import com.smalaca.shared.events.TrainingDraftSubmitted;
import com.smalaca.trainingmanagement.domain.TrainingDraft;
import com.smalaca.trainingmanagement.domain.TrainingDraftRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/training-drafts")
@RequiredArgsConstructor
public class TrainingManagementController {
    private final TrainingDraftRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public UUID submit(@RequestBody TrainingDraftRequest request) {
        UUID id = UUID.randomUUID();
        TrainingDraft trainingDraft = new TrainingDraft(id, request.getTitle(), request.getDescription(), "SUBMITTED");
        repository.save(trainingDraft);

        kafkaTemplate.send("training-draft-submitted", new TrainingDraftSubmitted(id, request.getTitle(), request.getDescription()));

        return id;
    }

    @GetMapping("/{id}")
    public TrainingDraft getStatus(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow();
    }
}

@Data
class TrainingDraftRequest {
    private String title;
    private String description;
}
