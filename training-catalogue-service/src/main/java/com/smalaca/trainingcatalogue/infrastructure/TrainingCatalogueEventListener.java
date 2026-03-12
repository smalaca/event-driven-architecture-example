package com.smalaca.trainingcatalogue.infrastructure;

import com.smalaca.shared.events.TrainingPublished;
import com.smalaca.trainingcatalogue.domain.TrainingCatalogueItem;
import com.smalaca.trainingcatalogue.domain.TrainingCatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingCatalogueEventListener {
    private final TrainingCatalogueRepository repository;

    @KafkaListener(topics = "training-published", groupId = "catalogue-group")
    public void onPublished(TrainingPublished event) {
        TrainingCatalogueItem item = new TrainingCatalogueItem(event.trainingDraftId(), event.title());
        repository.save(item);
    }
}
