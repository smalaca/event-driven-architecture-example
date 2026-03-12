package com.smalaca.review.infrastructure;

import com.smalaca.shared.events.TrainingDraftApproved;
import com.smalaca.shared.events.TrainingDraftRejected;
import com.smalaca.shared.events.TrainingDraftSubmitted;
import com.smalaca.review.domain.TrainingReview;
import com.smalaca.review.domain.TrainingReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingDraftSubmittedListener {
    private final TrainingReviewRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "training-draft-submitted", groupId = "review-group")
    public void onSubmitted(TrainingDraftSubmitted event) {
        // Dummy logic: reject if title contains "REJECT", otherwise approve
        if (event.title().toUpperCase().contains("REJECT")) {
            TrainingReview review = new TrainingReview(event.trainingDraftId(), "REJECTED", "Title contains REJECT");
            repository.save(review);
            kafkaTemplate.send("training-draft-rejected", new TrainingDraftRejected(event.trainingDraftId(), "Rejected " + event.title()));
        } else {
            TrainingReview review = new TrainingReview(event.trainingDraftId(), "APPROVED", "High quality content of " + event.title());
            repository.save(review);
            kafkaTemplate.send("training-draft-approved", new TrainingDraftApproved(event.trainingDraftId()));
        }
    }
}
