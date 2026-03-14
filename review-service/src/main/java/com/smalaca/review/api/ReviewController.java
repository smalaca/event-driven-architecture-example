package com.smalaca.review.api;

import com.smalaca.review.domain.ApplicationReview;
import com.smalaca.review.domain.ApplicationReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ApplicationReviewRepository repository;

    @GetMapping("/{applicationDraftId}")
    public ApplicationReview getReview(@PathVariable UUID applicationDraftId) {
        return repository.findById(applicationDraftId).orElseThrow();
    }

    @GetMapping
    public Iterable<ApplicationReview> findAll() {
        return repository.findAll();
    }
}
