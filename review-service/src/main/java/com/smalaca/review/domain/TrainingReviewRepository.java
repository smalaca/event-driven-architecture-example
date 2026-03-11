package com.smalaca.review.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface TrainingReviewRepository extends CrudRepository<TrainingReview, UUID> {
}
