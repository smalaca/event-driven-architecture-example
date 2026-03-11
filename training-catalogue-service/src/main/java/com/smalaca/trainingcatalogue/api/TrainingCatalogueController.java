package com.smalaca.trainingcatalogue.api;

import com.smalaca.trainingcatalogue.domain.TrainingCatalogueItem;
import com.smalaca.trainingcatalogue.domain.TrainingCatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingCatalogueController {
    private final TrainingCatalogueRepository repository;

    @GetMapping("/{trainingDraftId}")
    public TrainingCatalogueItem getStatus(@PathVariable UUID trainingDraftId) {
        return repository.findById(trainingDraftId).orElseThrow();
    }
}
