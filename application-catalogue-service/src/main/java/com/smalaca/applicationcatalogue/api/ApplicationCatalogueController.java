package com.smalaca.applicationcatalogue.api;

import com.smalaca.applicationcatalogue.domain.ApplicationCatalogueItem;
import com.smalaca.applicationcatalogue.domain.ApplicationCatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationCatalogueController {
    private final ApplicationCatalogueRepository repository;

    @GetMapping("/{applicationDraftId}")
    public ApplicationCatalogueItem getStatus(@PathVariable UUID applicationDraftId) {
        return repository.findById(applicationDraftId).orElseThrow();
    }

    @GetMapping
    public Iterable<ApplicationCatalogueItem> findAll() {
        return repository.findAll();
    }
}
