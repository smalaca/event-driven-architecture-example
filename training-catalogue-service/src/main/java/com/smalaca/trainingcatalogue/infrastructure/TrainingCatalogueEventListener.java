package com.smalaca.trainingcatalogue.infrastructure;

import com.smalaca.shared.events.TrainingDraftApproved;
import com.smalaca.shared.events.TrainingDraftRejected;
import com.smalaca.shared.events.TrainingDraftSubmitted;
import com.smalaca.shared.events.TrainingPublished;
import com.smalaca.trainingcatalogue.domain.TrainingCatalogueItem;
import com.smalaca.trainingcatalogue.domain.TrainingCatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingCatalogueEventListener {
    private final TrainingCatalogueRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "training-draft-submitted", groupId = "catalogue-group")
    public void onSubmitted(TrainingDraftSubmitted event) {
        TrainingCatalogueItem item = new TrainingCatalogueItem(event.trainingDraftId(), event.title(), "SUBMITTED");
        repository.save(item);
    }

    @KafkaListener(topics = "training-draft-approved", groupId = "catalogue-group")
    public void onApproved(TrainingDraftApproved event) {
        repository.findById(event.trainingDraftId()).ifPresent(item -> {
            item.setStatus("APPROVED");
            repository.save(item);
            kafkaTemplate.send("training-published", new TrainingPublished(item.getTrainingDraftId(), item.getTitle()));
        });
    }

    @KafkaListener(topics = "training-draft-rejected", groupId = "catalogue-group")
    public void onRejected(TrainingDraftRejected event) {
        repository.findById(event.trainingDraftId()).ifPresent(item -> {
            item.setStatus("REJECTED");
            repository.save(item);
        });
    }

    @KafkaListener(topics = "training-published", groupId = "catalogue-group")
    public void onPublished(TrainingPublished event) {
        repository.findById(event.trainingDraftId()).ifPresent(item -> {
            item.setStatus("PUBLISHED");
            repository.save(item);
        });
    }
}
