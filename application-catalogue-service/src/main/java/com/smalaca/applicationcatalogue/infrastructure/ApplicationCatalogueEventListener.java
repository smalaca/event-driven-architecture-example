package com.smalaca.applicationcatalogue.infrastructure;

import com.smalaca.shared.events.ApplicationPublished;
import com.smalaca.applicationcatalogue.domain.ApplicationCatalogueItem;
import com.smalaca.applicationcatalogue.domain.ApplicationCatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationCatalogueEventListener {
    private final ApplicationCatalogueRepository repository;

    @KafkaListener(topics = "application-published", groupId = "catalogue-group")
    public void onPublished(ApplicationPublished event) {
        ApplicationCatalogueItem item = new ApplicationCatalogueItem(event.applicationDraftId(), event.title(), event.description(), event.price());
        repository.save(item);
    }
}
