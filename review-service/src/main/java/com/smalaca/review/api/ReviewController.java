package com.smalaca.review.api;

import com.smalaca.review.domain.TrainingReview;
import com.smalaca.review.domain.TrainingReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final TrainingReviewRepository repository;

    @GetMapping("/{trainingDraftId}")
    public TrainingReview getReview(@PathVariable UUID trainingDraftId) {
        return repository.findById(trainingDraftId).orElseThrow();
    }

    @GetMapping
    public Iterable<TrainingReview> findAll() {
        return repository.findAll();
    }
}
