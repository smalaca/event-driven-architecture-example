package com.smalaca.review.infrastructure;

import com.smalaca.shared.events.ApplicationDraftApproved;
import com.smalaca.shared.events.ApplicationDraftRejected;
import com.smalaca.shared.events.ApplicationDraftSubmitted;
import com.smalaca.review.domain.ApplicationReview;
import com.smalaca.review.domain.ApplicationReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationDraftSubmittedListener {
    private final ApplicationReviewRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "application-draft-submitted", groupId = "review-group")
    public void onSubmitted(ApplicationDraftSubmitted event) {
        // Dummy logic: reject if title contains "REJECT", otherwise approve
        if (event.title().toUpperCase().contains("REJECT")) {
            ApplicationReview review = new ApplicationReview(event.applicationDraftId(), "REJECTED", "Title contains REJECT");
            repository.save(review);
            kafkaTemplate.send("application-draft-rejected", new ApplicationDraftRejected(event.applicationDraftId(), "Rejected " + event.title()));
        } else {
            ApplicationReview review = new ApplicationReview(event.applicationDraftId(), "APPROVED", "High quality content of " + event.title());
            repository.save(review);
            kafkaTemplate.send("application-draft-approved", new ApplicationDraftApproved(event.applicationDraftId()));
        }
    }
}
