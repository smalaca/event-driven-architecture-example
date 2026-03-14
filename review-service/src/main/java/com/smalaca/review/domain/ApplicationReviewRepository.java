package com.smalaca.review.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ApplicationReviewRepository extends CrudRepository<ApplicationReview, UUID> {
}
